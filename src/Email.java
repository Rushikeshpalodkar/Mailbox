import java.io.Serializable;
import java.util.GregorianCalendar;
/**
 * Name:Rushikesh Palodkar
 * ID:113808719
 * Class:Serializer
 */
public class Email implements Serializable {
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp = new GregorianCalendar();
    public static int count =0;

    /**
     * constructor for following parameters of email.
     * @param to
     * @param cc
     * @param bcc
     * @param subject
     * @param body
     */
    public Email(String to, String cc, String bcc, String subject, String body) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;

    }

// To print table according to index time and Subject
    @Override
    public String toString() {

        String result;
        Email.count++;
        String R1 =String.format("\n%-6s|%-15s%-20s|%-20s\n","Index","","Time","Subject");
        String R3 = "\n_________________________________________________________";
        String R2 ="\n"+ String.format("%-6s|%4s%-31s|%-20s\n",count,"",getTimestamp().getTime(),getSubject());
        if(count==1){
            result =R1+R3+R2;
        }
        else{
            result =R2;}
        return result;

    }

    /**
     * Accessor and mutation methods.
     * For all parameter.
     * @return
     */
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }


}
