package data.structure.array.size;

public interface IFixedArray {
  String traverse(); // Time :- O(n) || Space :- O(n)

  // void insertAtBeginning(String data); //
  // void insertAtIdx(String data, int idx);
  void insertAtEnd(String data) throws ArrayIndexOutOfBoundsException;
  // void deleteAtBeginning();
  // void deleteAtIdx(int idx);
  // void deleteAtEnd();
  // void deleteFirstOccurrence(String data);
  // void deleteAllOccurrence(String data);
}
