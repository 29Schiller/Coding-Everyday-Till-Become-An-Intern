public class LinkQueueApp{
   public static void main(String[] args){
      LinkQueue theQueue = new LinkQueue();
      theQueue.insert(20);                 // insert items
      theQueue.insert(40);

      theQueue.displayQueue();             // display queue

      theQueue.insert(60);                 // insert items
      theQueue.insert(80);

      theQueue.displayQueue();             // display queue

      theQueue.remove();                   // remove items
      theQueue.remove();

      theQueue.displayQueue();             // display queue
   }  // end main()
}  // end class LinkQueueApp
////////////////////////////////////////////////////////////////
