/*
Description: http://www.cs.sunysb.edu/~cse214/images/logo.gif  
HOMEWORK - SUMMER 2022

HOMEWORK 5 – due Friday, August 12th, before 10:00pm.

REMINDERS:

Be sure your code follows the coding style for CSE214.
Make sure you read the warnings about academic dishonesty. Remember, all work you submit for homework or exams MUST be your own work.
Click here to read through the steps to make sure you hand in your homework successfully. Also, login to your grading account and click "Submit Assignment" to upload and submit your assignment. Make sure that you submit the same files on both systems.
You may use any Java API class.
You may use Scanner, InputStreamReader, or any other class that you wish for keyboard input.
The goal of this assignment is to write a program that manages an email system (like a stripped down version of Outlook) except that you can’t send and receive emails (naturally). However, this program will have all of the usual email features like deleting emails, creating new emails, moving emails to different folders, adding and removing emails, and the like.


1. Write a fully documented class named Email. This class will contain the following information about each email.

private String to
The String literal which stores the “to” field.
private String cc
The String literal which stores the “cc” field.
private String bcc
The String literal which stores the “bcc” field.
private String subject
The String literal which stores the “subject” field.
private String body
The String literal which stores all of the text in the email’s body.
private GregorianCalendar timestamp
Represents the time that this email was created.
The Email class should have a Constructor, mutator and accessor methods for each instance variable. In order for your program to be able to save Email objects to a file, all objects in it must be serializeable, so this class must implement Serializable. 

2. Write a fully documented class named Folder. This class represents an email folder and will handle all of the logic for adding and removing emails. It contains the following data values:

·         private ArrayList<Email> emails

Stores all of the emails contained in this folder. (It can be any data collection, ArrayList is only an example)

·         private String name

Stores the name of the folder.

·         private String currentSortingMethod

Stores the current sorting method (however that you see fit) so that emails added can be properly sorted without having to first resort the folder. Notes: Default is date descending.

 

 

It also has the following methods (including the usual constructor and accessor/mutators methods):

·         public void addEmail(Email email)

Adds an email to the folder according to the current sorting method.

·         public Email removeEmail(int index)

Removes an email from the folder by index.

·         public void sortBySubjectAscending()

·         Sorts the emails alphabetically by subject in ascending order.

·         public void sortBySubjectDescending()

·         Sorts the emails alphabetically by subject in descending order.

·         public void sortByDateAscending()

·         Sorts the emails by date in ascending order.

·         public void sortByDateDescending()

·         Sorts the emails by date in descending order.

 

3. Write a fully documented class named Mailbox. This class represents an email box and it will contain all of the folders and the remaining logic. It contains the following data values:

·         private Folder inbox

Stores the inbox, which is a special folder which should never be allowed to be deleted or renamed. All new emails go here.

·         private Folder trash

·         Stores the trash, which is a special folder which should never be allowed to be deleted or renamed. When an email is deleted, it is moved here.

·         private ArrayList<Folder> folders

Stores all of the custom folders contained in the mailbox. (It can be any data collection, ArrayList is only an example)

·         public static Mailbox mailbox

Stores the main mailbox that will used by the application. This mailbox should be instantiated in the main method.


It also contains the following methods (and a main method):

·         public void addFolder(Folder folder)

Adds the given folder to the list of custom folders. Note: check to make sure a folder with that given name does not already exist. If it does, return an error in some manner.

·         public void deleteFolder(String name)

Removes the given folder from the list of custom folders

·         public void composeEmail()

Gets user input on the contents of the email and adds it to the inbox.

·         public void deleteEmail(Email email)

Moves the email to the trash. (This method shouldn’t remove any emails from folders, the method removeEmail should be called and then deleteEmail is called on the result)

·         public void clearTrash()

Removes all emails from the trash folder.

·         public void moveEmail(Email email, Folder target)

Takes the given email and puts in in the given folder. If the folder cannot be found, instead move it to the Inbox. (Again, this method shouldn’t remove any emails from folders, the method removeEmail should be called and then moveEmail is called on the result)

·         public Folder getFolder(String name)

Returns a folder by folder name.

 

 

 

           

4. When the program begins, it should see if the file "mailbox.obj" exists in the current directory. If so, it should initialize the mailbox with the data in this file using serialization. Otherwise, your program will start with an empty mailbox. Then, the program should provide the user with a menu with the following options.

A – Add folder

Prompts the user for folder name, and creates and adds a new folder with the specified name to the list of folders.

R – Remove folder

Prompts the user for folder name and removes the folder if a folder with the given name exists.

C – Compose email

Prompts the user for TO, CC, BCC, Subject, and then Body and creates a new email and adds it to the inbox.

F – View Folder

Prompts the user for folder name, if the folder is found, the folder submenu is opened for that specific folder which displays all of the emails in the folder.

I – View Inbox

Opens the folder submenu for the inbox.

T – View Trash

Opens the folder submenu for the trash.

E – Empty Trash

Empties the trash folder of all emails.

Q - Quit the program

Remember to save the contents of the mailbox to the file, "mailbox.obj".

There is no option to load or save a file in the menu. This is because you should automatically search for a file named "mailbox.obj" upon startup, and save to the file when the user wishes to quit the program.

 

Folder submenu:

M – Move email

Prompts the user for email index and then displays a list of folders and asks the user to input the name of the folder to move to. If the specified folder was not found, cancel the entire operation. User should be able to move to Inbox, Trash, and the current folder.

D – Delete email

Prompts the user for email index and moves the email to the trash folder.

V – View email contents

Prompts the user for email index and prints the information about the corresponding email.

SA – Sort by subject ascending

Sorts the emails by subject in ascending order.

SD – Sort by subject descending

Sorts the emails by subject in descending order.

DA – Sort by date ascending

Sorts the emails by date in ascending order.

DD – Sort by date descending

Sorts the emails by date in descending order.

R – Return to main menu


4. Include exception classes or additional classes as needed. 



OUTPUT FORMAT

Be sure to have a menu after each operation.
Display to the user any errors that seem appropriate at any point during execution of your program.
EXTRA CREDIT (4 points)

Use Graphical User Interface (GUI) for program input and display the results. (4 points)
//NOTE: The GUI must include headers (like the “Subject”, “Date” headers in your average mailbox system) and these headers must be clickable to sort. Clicking a header switches from ascending to descending (and vice versa) and changes the sort parameter to whatever the header specifies. And rather than accessing emails by index, all emails should have two buttons (Delete and Move) next to it that the user uses to delete and move emails from folders.

i.e. If the current sorting method is Date Ascending and the user clicks on the Subject header, the new sorting method is Subject Descending.


SAMPLE COMPARABLE CODE


import java.util.*;
 
/*
 * An example of type abstraction that implements Comparable
 * and Comparator interfaces.
 *
 * The Comparable/Comparator interfaces provide a standard means
 * for communication with yet unknown types of objects.
*/
/*      
public class CollectionsTester {
         public static void main(String[] args) {
           ArrayList<Employee> staff = new ArrayList<Employee>();
		   
           staff.add(new Employee("Joe",100000, 177700010));
           staff.add(new Employee("Jane",200000, 111100010));
           staff.add(new Employee("Bob",66666, 1999000010));
           staff.add(new Employee("Andy",77777, 188800010));
      
           Collections.sort(staff);                                      // Sort by salary
           System.out.println("Lowest paid employee: "+staff.get(0));    // Prints Bob
           
           Collections.sort(staff, new NameComparator());                // Sort by aplahabetical order
           System.out.println("First employee in list: "+staff.get(0));  // Prints Andy
          
           Collections.sort(staff, new IdComparator());                  // Sort by ID number
           System.out.println("Employee with lowest ID: "+staff.get(0)); // Prints Jane
         }
}
 
public class Employee implements Comparable {
        private String name;
        private int salary;
        private int id;
        public Employee(String initName, int initSal, int initId) {
                id     = initId;
                name = initName;
                salary = initSal;
        }
        public String getName(){ return name; }
        public int getSalary() { return salary; }
        public int getId(){ return id; }
        public void setSalary(int newSalary) {
                salary = newSalary;
        }
        public int compareTo(Object o) {
                Employee otherEmp = (Employee)o;
                if (this.salary == otherEmp.salary)
                        return 0;
                else if (this.salary > otherEmp.salary)
                        return 1;
                else
                        return -1;
        }
        public String toString() {
                return name + ", $" + salary + ", "+ id;
        }
}
 
public class NameComparator implements Comparator {
        public int compare(Object o1, Object o2) {
                Employee e1 = (Employee) o1;
                Employee e2 = (Employee) o2;
                return (e1.getName().compareTo(e2.getName()));
        }
}
 
public class IdComparator implements Comparator {
       public int compare(Object o1, Object o2) {
           Employee e1 = (Employee) o1;
           Employee e2 = (Employee) o2;
           if (e1.getId() == e2.getId())
               return 0;
           else if (e1.getId() > e2.getId())
               return 1;
           else
               return -1;
       }
}


EXAMPLES

ObjectInputStream.readObject():
MyClass myObject; //MyClass implements Serializable
try {
  //If file is found, open it
  FileInputStream   file = new FileInputStream("mySaveFile.obj");
  ObjectInputStream fin  = new ObjectInputStream(file);
  myObject = (MyClass) fin.readObject();
  file.close();
} catch(IOException a){}
  catch(ClassNotFoundException c){} //Bottoms up!
//Note that myObject may still be null
ObjectOutpurStream.writeObject():
try {
  FileOutputStream   file = new FileOutputStream("mySaveFile.obj");
  ObjectOutputStream fout = new ObjectOutputStream(file);
  fout.writeObject(myObject);
  fout.close();
} catch(IOException a) { /*Error*/ }
/*
System.out.printf():
System.out.printf("%4d %4d %4d %-40s", 1, 2, 3, "A string");
System.out.printf("%4d %4d %4d %-40s", 10, 20, 30, "A longer string");
System.out.printf("%4d %4d %4d %-40s", 10, 100, 1000, "Very longer than previous strings");
//output:
//   1    2    3 A string
//  10   20   30 A longer string
//  10  100 1000 Very longer than previous strings
SAMEPL INPUT/OUTPUT

Comments in green. User input in black. Program output in blue.


Previous save not found, starting with an empty mailbox.

 

Mailbox:

--------

Inbox

Trash

 

A – Add folder

R – Remove folder

C – Compose email

F – Open folder

I – Open Inbox

T – Open Trash

Q – Quit

 

Enter a user option: A

 

Enter folder name: Other Folder

 

Mailbox:

--------

Inbox

Trash

Other Folder

 

//Menu not included to save space.

 

Enter a user option: C

 

Enter recipient (To): phillip.smith@stonybrook.edu, psmith@ic.sunysb.edu


Enter carbon copy recipients (CC): psmith@cs.sunysb.edu

 

Enter blind carbon copy recipients (BCC): phroph@yahoo.com

 

Enter subject line: Testing

 

Enter body: This is a test of the mailbox program!

 

Email successfully added to Inbox.

 

Mailbox

--------

Inbox

Trash

Other Folder

 

//Menu not included to save space.

 

Enter a user option: I

 

Inbox

 

Index |        Time       | Subject

-----------------------------------

  1   |  12:38PM 4/8/2012 | Testing

 

M – Move email

D – Delete email

V – View email contents

SA – Sort by subject line in ascending order

SD – Sort by subject line in descending order

DA – Sort by date in ascending order

DD – Sort by date in descending order

R – Return to mailbox

 

Enter a user option: M

 

Enter the index of the email to move: 1

 

Folders:

Inbox

Trash

Other Folder

 

Select a folder to move “Testing” to: Other Folder

 

“Testing” successfully moved to Other Folder.

 

Inbox

 

Inbox is empty.

 

//Menu not included to save space.

 

Enter a user option: R

 

Mailbox:

--------

Inbox

Trash

Other Folder

 

//Menu not included to save space.

 

Enter a user option: F

 

Enter folder name: Other Folder

 

Other Folder

 

Index |        Time       | Subject

-----------------------------------

  1   |  12:38PM 4/8/2012 | Testing

 

//Menu not included to save space.

 

Enter a user option: V

 

Enter email index: 1

 

To: phillip.smith@stonybrook.edu, psmith@ic.sunysb.edu

CC: psmith@cs.sunysb.edu

BCC: phroph@yahoo.com

Subject: Testing

This is a test of the mailbox program!

 

Other Folder

 

Index |        Time       | Subject

-----------------------------------

  1   |  12:38PM 4/8/2012 | Testing

 

//Menu not included to save space.

 

Enter a user option: D

 

Enter email index: 1

 

“Testing” has successfully been moved to the trash.

 

Other Folder

 

Other Folder is empty.

 

//Menu not included to save space.

 

Enter a user option: R

 

Mailbox

--------

Inbox

Trash

Other Folder

 

//Menu not included to save space.

 

Enter a user option: E

 

1 item(s) successfully deleted.

 

Mailbox

--------

Inbox

Trash

Other Folder

 

//Menu not included to save space.

 

Enter a user option: Q

 

Program successfully exited and mailbox saved.
*/


import java.awt.*;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Name:Rushikesh Palodkar
 * ID:113808719
 * Class:Mailbox
 */
public class Mailbox implements Serializable {
    private static Folder inbox;//Never allowed to rename or deleted
    private static Folder trash;
    private static Map<String, Folder> folders;
    public static Mailbox mailbox;
    public static int trashCount =0;

    /**
     *Main method.
     * @param args
     */
    public static void main(String[] args) {
/**
 * Creation:
 * mailbox
 * inbox
 * thrash
 * folder
 */



        mailbox = new Mailbox();
        mailbox.inbox = new Folder("inbox");
        mailbox.trash = new Folder("trash");
        mailbox.folders = new HashMap<>();
        boolean exit = false;
//       Email email3 =new Email("fdsa","fasd","aefa","aef","faea");
//      System.out.println(email3.getTimestamp().getTime());

/**
 * input and output sterm as in lecture notes
 */

        try {
            FileInputStream file = new FileInputStream("mailbox.obj");
            ObjectInputStream inputStream = new ObjectInputStream(file);
            Mailbox obj = (Mailbox) inputStream.readObject();
            if (obj != null) {
                mailbox = obj;
                System.out.println("Loaded mailbox from obj.mailbox file. ");
            } else {
                mailbox = new Mailbox();
                mailbox.inbox = new Folder();
                mailbox.trash = new Folder();
                System.out.println("Setting up new mailbox");
            }
            inputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            mailbox = new Mailbox();
            mailbox.inbox = new Folder();
            mailbox.trash = new Folder();
            System.out.println("New start empty mailbox");
        }

//while loop which when you enter "Q" and it is true and will print the menu.
        while (!exit) {

            System.out.println(
                    "Mailbox:\n" +
                            "--------\n" +
                            "Inbox\n" +
                            "Trash");
            for (Map.Entry<String, Folder> folder : folders.entrySet()) {
                System.out.println(folder.getKey());
            }
            System.out.println(" \n" +
                    "A – Add folder\n" +
                    "R – Remove folder\n" +
                    "C – Compose email\n" +
                    "F – Open folder\n" +
                    "I – Open Inbox\n" +
                    "T – Open Trash\n" +
                    "E – Empty Trash\n"+
                    "Q – Quit");
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            System.out.println("Enter a user option:");
            String choice = String.valueOf(sc.next().charAt(0));
            /**
             * Runs according to the above cases and while composing sort the emails in date descending order.
             */
            switch (choice) {
                /**
                 * Adds a new folder by creating it according to cases.
                 */
                case "A":
                case "a":
                    for (Map.Entry<String, Folder> folder : folders.entrySet()) {
                        System.out.println(folder.getKey());
                    }
                    System.out.println("Enter the name:");

                    String name = sc.next();

                    Folder newFolder = new Folder(name);
                    addFolder(newFolder);
                    break;
                /**
                 * Removes the folder according to the given index.
                 */
                case "r":
                case "R":
                    System.out.println("Enter the name to delete: ");
                    String toDelete = sc.next();
                    if (toDelete.equalsIgnoreCase("inbox") || toDelete.equalsIgnoreCase("trash"))
                        System.out.println("Cannot delete that folder");
                    else {
                        deleteFolder(toDelete);
                        System.out.println(
                                "Mailbox:\n" +
                                        "--------\n" +
                                        "Inbox\n" +
                                        "Trash");
                        for (Map.Entry<String, Folder> folder : folders.entrySet()) {
                            System.out.println(folder.getKey());
                        }
                    }
                    break;
                /**
                 * compose email and sort my date descending.
                 */
                case "c":
                case "C":

                    System.out.println("Compose email ");
                    mailbox.composeEmail();
                    System.out.println("Email added to inbox");
                    break;
                /**
                 * in this case it prints the specific folder
                 * break according to cases
                 */
                case "F":
                case "f":
                    Email.count=0;
                    System.out.println(
                            "Mailbox:\n" +
                                    "--------\n" +
                                    "Inbox\n" +
                                    "Trash");
                    for (Map.Entry<String, Folder> folder : folders.entrySet()) {
                        System.out.println(folder.getKey());
                    }
                    //System.out.println("Enter a user option:");
                    System.out.println("Enter folder name:");
                    String folderName = sc.next();

                    if (folderName.equalsIgnoreCase("inbox")) {
                        for (Email email : inbox.getEmails()) {
                            System.out.println(email.toString());
                        }
                        operationOnSubMenu(inbox);
                    } else if (folderName.equalsIgnoreCase("trash")) {
                        for (Email email : trash.getEmails()) {
                            System.out.println(email.toString());
                        }
                        operationOnSubMenu(trash);
                    }
                    else {
                        Folder fol = folders.get(folderName);
                        if (fol == null)
                            System.out.println("Folder " + folderName + " does not exist");
                        else {
                            for (Email email : fol.getEmails()) {
                                System.out.println(email.toString());
                            }
                            operationOnSubMenu( fol);
                        }
                    }
                    break;
                /**
                 * this prints inbox
                 */
                case "I":
                case "i":
                    Email.count=0;
                    System.out.println("Inbox:");
                    for (Email email : inbox.getEmails()) {
                        System.out.println(email.toString());
                    }
                    operationOnSubMenu(inbox);
                    break;
                case "T":
                case "t":
                    Email.count=0;
                    System.out.println("Opening Trash");
                    for (Email email : trash.getEmails()) {
                        System.out.println(email.toString());
                    }
                    operationOnSubMenu(trash);
                    break;
                /**
                 * Empty the thrash and will print number of element removed
                 */
                case "E":
                case "e":
                    trashCount = trash.getEmails().size();
                    for(int i=0; i<trashCount;i++) {
                        trash.getEmails().remove(i);
                    }
                    System.out.println(trash.getEmails().size()+1 +" Element(s) removed.\n Trash is empty.");
                    break;
                /**
                 * exits and saves the object by calling writeObject
                 */
                case "Q":
                case "q":
                    exit = true;

                    try {
                        FileOutputStream file = new FileOutputStream("mailbox.obj");
                        ObjectOutputStream fout = new ObjectOutputStream(file);
                        fout.writeObject(mailbox);
                        fout.close();
                    } catch (IOException a) {
                        System.out.println("File error");
                    }
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * will create folder according to the parameters
     * @param newFolder
     */
    public static void addFolder(Folder newFolder) {
        if (folders.containsKey(newFolder.getName())) {
            throw new IllegalArgumentException("Folder already exists");
        } else {
            folders.put(newFolder.getName(), newFolder);
        }
    }

    /**
     * method to delete folder using name of the folder as a parameter
     * @param name
     */
    public static void deleteFolder(String name) {
        if (folders.containsKey(name)) {
            folders.remove(name);
        } else {
            throw new IllegalArgumentException("Folder not found");
        }
    }

    /**
     * Method to compose Email according to the users enter parameters
     */
    public void composeEmail() {
        Scanner sc2 = new Scanner(System.in);
        sc2.useDelimiter("\n");
        System.out.println("To: ");
        String to = sc2.nextLine();
        System.out.println("cc: ");
        String cc = sc2.nextLine();
        System.out.println("BCC: ");
        String bcc = sc2.nextLine();
        System.out.println("Subject: ");
        String subject = sc2.nextLine();
        System.out.println("Body: ");
        String body = sc2.next();
        Email email = new Email(to, cc, bcc, subject, body);
        inbox.addEmail(email);

    }

    public void deleteEmail(Email email) {
        moveEmail(email, trash);
    }

    public static void moveEmail(Email email, Folder target) {
        if (folders.containsValue(email) && target != null) {
            target.addEmail(email);
        } else
            System.out.println("Cant add email because email dose not exist");
    }

    /**
     * This method prints the submenu whenever it is required.
     */
    public static void printSubMenu() {

        System.out.println(
                "M – Move email\n" +
                        "\n" +
                        "D – Delete email\n" +
                        "\n" +
                        "V – View email contents\n" +
                        "\n" +
                        "SA – Sort by subject line in ascending order\n" +
                        "\n" +
                        "SD – Sort by subject line in descending order\n" +
                        "\n" +
                        "DA – Sort by date in ascending order\n" +
                        "\n" +
                        "DD – Sort by date in descending order\n" +
                        "\n" +
                        "R – Return to mailbox");
    }

    /**
     * This method will take 2 parameters and will do oprations according to the paramether using switch case
     * will accept folder on which we have to perform the operation.
     * @param currentFolder
     */
    public static void operationOnSubMenu(Folder currentFolder) {
        Scanner sc1 = new Scanner(System.in);
        sc1.useDelimiter("\n");
        String option;
        boolean subMenu = true;
        while(subMenu) {//This will run until it is "r" and if it is "r" This loop will terminate and will use the main menu to perfome the orations
            printSubMenu();
            System.out.println("Enter user option ");
            option = sc1.next();
            if(option == null) {
                continue;
            }
            if(option.length() == 1){
                option= option.substring(0);
            }
            else {
                option =option.substring(0, 2);
            }
            if(option.toUpperCase().contains("MDVSASDDADDR")){
                option ="r";
            }

            switch(option) { //Switch case for the sub menu.

                case "m":
                case "M": // In this case it moves the email to specific folder
                    Email.count=0;
                    System.out.println("Enter the index of the email to move");
                    int index = sc1.nextInt();
                    System.out.println(
                            "Mailbox:\n" +
                                    "--------\n" +
                                    "Inbox\n" +
                                    "Trash");
                    for (Map.Entry<String, Folder> folder : folders.entrySet()) {
                        System.out.println(folder.getKey());
                    }
                    Folder f;
                    System.out.println("Select the email folder to move");
                    String movingFolder = sc1.next();
                    if(folders.containsKey(movingFolder)){

                        if (movingFolder.equalsIgnoreCase("inbox")) {
                            f = inbox;
                            f.addEmail(currentFolder.getEmails().get(index - 1));
                        } else if (movingFolder.equalsIgnoreCase("trash")) {
                            f = trash;
                            f.addEmail(currentFolder.getEmails().get(index - 1));
                            trashCount++;
                        } else {
                            // if(folders.containsValue(movingFolder))
                            f = folders.get(movingFolder);
                            if (f == null) {
                                System.out.println("f is null");
                            }
                            f.addEmail(currentFolder.getEmails().get(index - 1));
                            currentFolder.getEmails().remove(index - 1);

                        }
                        System.out.println("'" + f.getEmails().get(index - 1).getSubject() + "'" + " has been moved to " + movingFolder);
                        if (inbox.getEmails().isEmpty()) {
                            System.out.println("Inbox is Empty");
                        }
                    }
                    break;
                case "D":
                case "d"://In this case it Delete email accodeing to the given conditions
                    Email.count=0;
                    System.out.println("Enter Index of email to be deleted");
                    int indexToDelete = sc1.nextInt();

                    if (indexToDelete < 0 || indexToDelete > currentFolder.getEmails().size()||currentFolder.getEmails().size()<0||currentFolder.getEmails() == null) {
                        System.out.println("Index is not in the range");
                       break;
                    }
                    trash.addEmail(currentFolder.getEmails().get(indexToDelete - 1));
                    currentFolder.getEmails().remove(indexToDelete - 1);
                    System.out.println("'"+trash.getEmails().get(indexToDelete - 1).getSubject()+"'"+  " has been moved to trash");
                    if(currentFolder.getEmails().isEmpty()){
                        System.out.println(currentFolder.getName()+" is Empty");
                    }else{System.out.println(currentFolder.getName());
                        for (Email email : currentFolder.getEmails()) {
                            System.out.println(email.toString());
                        }
                    }
                    break;
                case "v":
                case "V": //Views the email of the specific folder.
                    Email.count=0;
                    System.out.println("Enter email index ");
                    int emailIndextoView = sc1.nextInt();
                    if(currentFolder.getEmails().size()>=emailIndextoView) {
                        Email thisIs = currentFolder.getEmails().get(emailIndextoView - 1);
                        System.out.println("\n" + "To: " + thisIs.getTo() + "\n" + "Cc: " + "" + thisIs.getCc() + "\n" + "Bcc: " + thisIs.getBcc() + "\n" + "Subject: " + thisIs.getSubject() + "\n" + thisIs.getBody());
                        System.out.println(currentFolder.getEmails().get(emailIndextoView - 1).toString());
                    }
                    break;
                case "SA":
                case "sa":// sorts in ascending order
                    Email.count=0;
                    currentFolder.sortSubjectAscending();
                    System.out.println("Sort by subject line in ascending order");
                    for (Email email : currentFolder.getEmails()) {
                        System.out.println(email.toString());
                    }
                    break;
                case "SD":
                case "sd"://sorts in descending order.
                    Email.count=0;
                    currentFolder.sortSubjectDecending();
                    System.out.println("Sort by subject Descending");
                    for (Email email : currentFolder.getEmails()) {
                        System.out.println(email.toString());
                    }
                    break;
                case "DA":
                case "da"://Sort  by date ascending
                    Email.count=0;
                    currentFolder.sortByDateAscending();
                    System.out.println("Sort by Date Ascending");
                    for (Email email : currentFolder.getEmails()) {
                        System.out.println(email.toString());
                    }
                    break;
                case "DD":
                case "dd"://sort by date descending
                    Email.count=0;
                    currentFolder.sortByDatedes();
                    System.out.println("Sort by Date descending");
                    for (Email email : currentFolder.getEmails()) {
                        System.out.println(email.toString());
                    }
                    break;
                case "r":
                case "R":// returns to main menu.
                    System.out.println("Returning to main menu");
                    subMenu = false;
                    break;
            }

        }
    }
    // Accessor and mutator method for the class Mailbox

    public Folder getInbox() {
        return inbox;
    }

    public void setInbox(Folder inbox) {
        this.inbox = inbox;
    }

    public Folder getTrash() {
        return trash;
    }

    public void setTrash(Folder trash) {
        this.trash = trash;
    }
}
