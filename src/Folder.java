import java.io.Serializable;
import java.lang.*;
import java.util.*;
/**
 * Name:Rushikesh Palodkar
 * ID:113808719
 * Class:Mailbox
 */
public class Folder implements Serializable{
    private ArrayList<Email> emails  = new ArrayList<>(); // (It can be any data collection, ArrayList is only an example)
    private String name;//Stores the name of the folder.
    private static String currentSortingMethod;//Stores the current sorting method && Default is date descending.

    /**
     * Default constructor for Folder.
     */

    public Folder(String name) {
        this.name = name;
        currentSortingMethod = "date Descending";
        //emails.sort((e1,e2)-> e2.getTimestamp().compareTo(e1.getTimestamp()));//Notes: Default is date descending.
    }

    public Folder() {
        name = "name";
    }

    public void addEmail(Email email){
      emails.add(email);
     sort();
      }

private void sort() {
    switch (currentSortingMethod) {
        case "Subject Ascending":
            emails.sort((e1,e2) -> e1.getSubject().compareTo(e2.getSubject()));
            break;
        case"Subject Descending":
            emails.sort((e1,e2)-> e2.getSubject().compareTo(e1.getSubject()));
            break;
        case"date ascending":
            emails.sort((e1,e2)-> e1.getTimestamp().getTime().compareTo(e2.getTimestamp().getTime()));
            break;
        case"date Descending":
            emails.sort((e1,e2)-> e2.getTimestamp().getTime().compareTo(e1.getTimestamp().getTime()));
            break;
        default:
            emails.sort((e1,e2)-> e2.getTimestamp().getTime().compareTo(e1.getTimestamp().getTime()));
    }
}
public Email removeEmail(int index){
        if(index <0 || index> emails.size()){
             throw new ArrayIndexOutOfBoundsException("Check your Index");
        }
        return emails.remove(index); //As al emails are stored in array list we can remove them using remove.
}

public void sortSubjectAscending(){
        currentSortingMethod = "Subject Ascending";
        sort();

}
public void sortSubjectDecending(){
        currentSortingMethod = "Subject Descending";
        sort();

}
public void sortByDateAscending(){

       currentSortingMethod = "date ascending";
    sort();

}
    public void sortByDatedes(){

        currentSortingMethod = "date Descending";
        sort();

    }


    /**
     * Accessor and mutator methods of class Folder.
     * @return
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }
}
