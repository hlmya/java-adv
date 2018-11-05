package pr;
//basic thread introduction
//https://www.youtube.com/watch?v=YdlnEWC-7Wo
//class HelloThread extends Thread {   
/*
class Runner implements Runnable {

   @Override
   public void run() {
       int index = 1;
 
       for (int i = 0; i < 10; i++) {
           System.out.println("  - HelloThread running " + index++);
 
           try {
               Thread.sleep(200);
           } catch (InterruptedException e) {
           }
 
       }
       System.out.println("  - ==> HelloThread stopped");
   }
}*/
   
public class HelloMain {

	public static void main(String[] args) throws InterruptedException {
		 
	       int idx = 1;
	 
	       for (int i = 0; i < 2; i++) {
	 
	           System.out.println("Main thread running " + idx++);
	          
	           Thread.sleep(500);
	       }
	 
	       //HelloThread helloThread = new HelloThread();
	       //Thread mythread = new Thread(new Runner());
	       Thread mythread = new Thread(new Runnable() {

			@Override
			public void run() {
				int index = 1;
				 
			       for (int i = 0; i < 10; i++) {
			           System.out.println("  - HelloThread running " + index++);
			 
			           try {
			               Thread.sleep(200);
			           } catch (InterruptedException e) {
			           }
			 
			       }
			       System.out.println("  - ==> HelloThread stopped");
			}
	    	   
	       });
	       
	       
	       // Run thread
	       //helloThread.start();
	       mythread.start();
	 
	       for (int i = 0; i < 3; i++) {
	           System.out.println("Main thread running " + idx++);
	           Thread.sleep(500);
	       }
	 
	       System.out.println("==> Main thread stopped");
	   }

}
