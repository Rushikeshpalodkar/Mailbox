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
