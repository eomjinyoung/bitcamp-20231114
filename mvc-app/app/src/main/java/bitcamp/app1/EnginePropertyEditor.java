package bitcamp.app1;

import java.beans.PropertyEditorSupport;
import java.sql.Date;

public class EnginePropertyEditor extends PropertyEditorSupport {

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    String[] values = text.split(","); // model,cc,valve

    Engine engine = new Engine();
    engine.setModel(values[0]);
    engine.setCc(Integer.parseInt(values[1]));
    engine.setValve(Integer.parseInt(values[2]));

    this.setValue(engine);
  }
}
