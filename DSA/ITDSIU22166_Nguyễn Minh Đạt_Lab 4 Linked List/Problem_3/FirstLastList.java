public class FirstLastList{
    private Link3 first;               // ref to first item
    private Link3 last;                // ref to last item
// -------------------------------------------------------------
    public FirstLastList()  {           // constructor
       
        first = null;                  // no items on list yet
        last = null;
    }
// -------------------------------------------------------------
    public boolean isEmpty()          // true if no links
        { return first==null; }
// -------------------------------------------------------------
    public void insertLast(long dd){ // insert at end of list
        Link3 newLink = new Link3(dd);   // make new link
        if( isEmpty() )                // if empty list,
            first = newLink;            // first --> newLink
        else
            last.next = newLink;        // old last --> newLink
        last = newLink;                // newLink <-- last
    }
    // -------------------------------------------------------------
    public long deleteFirst(){         // delete first link
                                      // (assumes non-empty list)
        long temp = first.dData;
        if(first.next == null)         // if only one item
            last = null;                // null <-- last
        first = first.next;            // first --> old next
        return temp;
    }
    // -------------------------------------------------------------
    public void displayList(){
        Link3 current = first;          // start at beginning
        while(current != null)         // until end of list,
            {
            current.displayLink();      // print data
            current = current.next;     // move to next link
        }
        System.out.println("");
    }
// -------------------------------------------------------------
}// end class FirstLastList
////////////////////////////////////////////////////////////////
