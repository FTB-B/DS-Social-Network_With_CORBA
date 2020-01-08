


import social_network_system.*;

public class DMessageImpl extends DMessagePOA {
    String user;
    String group;

    public DMessageImpl(String user, String group)
    {
        super();
        this.user = user;
        this.group = group;
    }

    public void GetUniMessage(String message, String sender)
    {
        int n = Groupform.unimsgs.size();
        int i;
        for(i = 0; i < n; i++)
        {
            if(Groupform.unimsgs.get(i).GetPeer().equals(sender))
            {
                Groupform.unimsgs.get(i).GetMessage(sender + ": " + message);
                break;
            }
        }
        if(i == n)
        {
            UniMessage unimsg = new UniMessage(user,sender , group);
           Groupform.unimsgs.add(unimsg);
            unimsg.setVisible(true);
            unimsg.GetMessage(sender + ": " + message);
        }
    }
    public void GetGroupMessage(String message, String sender){
        if(Groupform.groupmsg.GetGroup().equals(group))
        {
            
            Groupform.groupmsg.GetMessage(sender + ": " + message);
        }
        else
        {
            GroupMessage groupmsg = new GroupMessage(user,  group);
           
            Groupform.groupmsg = groupmsg;
            groupmsg.setVisible(true);
            groupmsg.GetMessage(sender + ": " + message);
        }
    }
 
}
