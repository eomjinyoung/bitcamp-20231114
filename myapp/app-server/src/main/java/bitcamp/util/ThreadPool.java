package bitcamp.util;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool implements Pooling<WorkerThread> {

  List<WorkerThread> list = new ArrayList<>();

  @Override
  public WorkerThread get() {
    if (list.size() > 0) {
      WorkerThread t = list.remove(0);
      System.out.printf("기존 스레드를 꺼냄!(%s)\n", t.getName());
      return t;
    }
    WorkerThread thread = new WorkerThread(this);
    thread.start();
    System.out.printf("새 스레드를 만들어 리턴!(%s)\n", thread.getName());
    return thread;
  }

  @Override
  public void revert(WorkerThread thread) {
    list.add(thread);
  }
}
