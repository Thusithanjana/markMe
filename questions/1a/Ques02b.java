
class Calculation {
   private double sum = 0;
   public double getSum() {  // 1 marks for getter
      return sum;
   }
   // 0.5 marks = for data[] array, 0.5 marks =  for rest of the ComputeSum() 
   public void ComputeSum(int data[], int start, int end) {
      double msum = 0;

      for (int r = start; r <= end; r++) {
         msum = data[r] + msum;  // correct calculation - 1 mark
      }
      synchronized (this) {  // synchronized used anywhere properly - 1 mark
         sum = sum + msum;
      }
      // Synchronized can be used for the entire code segment as well
   }

}

// 0.5 marks for implementing the class ParallelThread correctly (using runnable is also corect)
//
class ParallelThread extends Thread {
   private Calculation type;  // 0.25 x 4 = 1 marks for properties
   private int data[];
   private int start;
   private int end;
   // 0.5 marks = using type and data[] properly as parameters
   public ParallelThread(Calculation type, int data[], int start, int end) {
      this.type = type;
      this.data = data;
      this.start = start;
      this.end = end;         // using constructor correctly 1 mark
   }
   public void run() {
      type.ComputeSum(data, start, end);   // proper method call 1 mark
   }
   
}

class MainThreadApp {
   public static void main(String args[]) {

      int data[] = new int[100001];

      for (int r=0; r<= 100000; r++)
         data[r] = r;   // 0.5 marks

      Calculation mycalc = new Calculation();  

      ParallelThread mthread1 = new ParallelThread(mycalc, data, 1, 25000); // 0.5 marks
      ParallelThread mthread2 = new ParallelThread(mycalc, data, 25001,50000); // 0.5 marks
      ParallelThread mthread3 = new ParallelThread(mycalc, data, 50001,75000); // 0.5 marks
      ParallelThread mthread4 = new ParallelThread(mycalc, data, 75001,100000); // 0.5 marks

      mthread1.start();
      mthread2.start();  // 0.25 x 4 = 1 mark  marks if both start() used correctly
      mthread3.start();  
      mthread4.start();  

      try {
         mthread1.join();  // 1.5 marks for the 4 correct joins 
         mthread2.join();  // give partial marks as appropriate
         mthread3.join();  // only 3 joins 1 mark, 2 joins 0.5 marks
         mthread4.join();
      } catch (InterruptedException e) {   // try catch - 0.5 marks
      }
      System.out.println("Ans is = " + mycalc.getSum()); // Ans = 5000050000  // 0.5 marks
   }

}
