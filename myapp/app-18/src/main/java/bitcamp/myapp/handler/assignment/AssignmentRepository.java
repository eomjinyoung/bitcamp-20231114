package bitcamp.myapp.handler.assignment;

import bitcamp.myapp.vo.Assignment;

public class AssignmentRepository {

  Assignment[] assignments = new Assignment[3];
  int length = 0;

  public void add(Assignment assignment) {
    if (this.length == this.assignments.length) {
      int oldSize = this.assignments.length;
      int newSize = oldSize + (oldSize >> 1);

      Assignment[] arr = new Assignment[newSize];
      for (int i = 0; i < oldSize; i++) {
        arr[i] = this.assignments[i];
      }

      this.assignments = arr;
    }

    this.assignments[this.length++] = assignment;
  }

  public Assignment remove(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }

    Assignment deleted = this.assignments[index];

    for (int i = index; i < (this.length - 1); i++) {
      this.assignments[i] = this.assignments[i + 1];
    }
    this.assignments[--this.length] = null;

    return deleted;
  }

  public Assignment[] toArray() {
    Assignment[] arr = new Assignment[this.length];
    for (int i = 0; i < this.length; i++) {
      arr[i] = this.assignments[i];
    }
    return arr;
  }

  public Assignment get(int index) {
    if (index < 0 || index >= this.length) {
      return null;
    }
    return this.assignments[index];
  }

  public Assignment set(int index, Assignment assignment) {
    if (index < 0 || index >= this.length) {
      return null;
    }

    Assignment old = this.assignments[index];
    this.assignments[index] = assignment;

    return old;
  }
}
