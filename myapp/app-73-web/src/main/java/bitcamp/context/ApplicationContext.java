package bitcamp.context;

import bitcamp.util.Component;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

  ApplicationContext parent;
  String[] basePackages;
  Map<String, Object> beans = new HashMap<>();

  public ApplicationContext(Map<String, Object> beanMap, String... basePackages) throws Exception {
    beans.putAll(beanMap);
    this.basePackages = basePackages;

    for (String basePackage : basePackages) {
      findComponents(new File("./build/classes/java/main"), basePackage, "");
    }
  }

  public ApplicationContext(ApplicationContext parent, String... basePackages) throws Exception {
    this.parent = parent;
    this.basePackages = basePackages;

    for (String basePackage : basePackages) {
      findComponents(new File("./build/classes/java/main"), basePackage, "");
    }
  }

  private void findComponents(File dir, String basePackage, String packageName) throws Exception {
    File[] files = dir.listFiles(file ->
        file.isDirectory() || (file.isFile()
            && !file.getName().contains("$")
            && file.getName().endsWith(".class")));

    if (packageName.length() > 0) {
      packageName += ".";
    }

    for (File file : files) {
      if (file.isFile()) {
        if (!packageName.startsWith(basePackage)) {
          continue;
        }

        Class<?> clazz = Class.forName(packageName + file.getName().replace(".class", ""));
        Component compAnno = clazz.getAnnotation(Component.class);
        if (compAnno == null) {
          continue;
        }

        Constructor<?> constructor = clazz.getConstructors()[0];
        Parameter[] params = constructor.getParameters();
        Object[] args = getArguments(params);
        beans.put(
            compAnno.value().length() == 0 ? clazz.getName() : compAnno.value(),
            constructor.newInstance(args));
        System.out.println(clazz.getName() + " 객체 생성!");

      } else {
        findComponents(file, basePackage, packageName + file.getName());
      }
    }
  }

  private Object[] getArguments(Parameter[] params) {
    Object[] args = new Object[params.length];
    for (int i = 0; i < params.length; i++) {
      args[i] = getBean(params[i].getType());
    }
    return args;
  }

  private Object getBean(Class<?> type) {
    // 현재 IoC 컨테이너에서 찾는다.
    Collection<Object> objs = beans.values();
    for (Object obj : objs) {
      if (type.isInstance(obj)) {
        return obj;
      }
    }

    // 현재 IoC 컨테이너에 객체가 없다면, 부모 IoC 컨테이너에서 찾는다.
    if (parent != null) {
      return parent.getBean(type);
    }

    // 부모 IoC 컨테이너가 없다면,
    return null;
  }

  public Collection<Object> getBeans() {
    return beans.values();
  }
}
