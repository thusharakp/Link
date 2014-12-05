import java.io.*;
import java.net.*;
public class Switch implements Runnable
{
	Thread t1;//t2;	
	String recdata="",data="";
	int swport;
	int opreq=1;
	int operes=2;
	int desport;
	byte[]senddata=new byte[1024];
	byte[]recvdata=new byte[1024];
	DatagramSocket dp;
	String ip[]={"192.168.1.130","165.165.20.5"};
        String mac[]={" d0:27:88:e8:37:6a ","8A:BC:E3:FA:EA"};
	int portlist[]={8088,8055};
	public Switch(int p)
	{
		swport=p;
		try
		{
			this.swport=swport;
			t1=new Thread(this);
			//t2=new Thread(this);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void run()
	{
		while(true)
		{
			if(Thread.currentThread()==t1)
			{	
			//	send(swport);
			//}
			//else
			//{
			try
			{
			//		Thread.sleep(500);
				dp=new DatagramSocket(swport);
				
			}			
				
				catch(Exception e)
				{
					System.out.println(e);
				}	
				recev();
				//DatagramSocket dp=new DatagramSocket(swport);
			}			
		}
	}
	/*public void send(int p)
	{
		DatagramSocket dp=new DatagramSocket(port);
	}*/
	public void recev()
	{
		try
		{
		//DatagramSocket dp=new DatagramSocket(port);
		DatagramPacket receivePacket = new DatagramPacket(recvdata, recvdata.length);
                dp.receive(receivePacket);
		String recd = new String( receivePacket.getData());
		//recdata= receivePacket.getData();
		 //String st=new String(data);
		String str=new String(recd);
            String str1=str.trim();
            System.out.println("Received from device : "+str);
            String st[]=str1.split("::");
            String msg=st[0];//msg
            int op=Integer.parseInt(st[1]);//opcode
            String dip=st[2];//deviceip
            //String ip1=dip;
            String dmac=st[3];//dmac
            String desip=st[4];//destip
            //String ip2=ipa;
            String destmac=st[5];//destmac
           int devport=Integer.parseInt(st[6]);//devport
           // devadrs=dp.getAddress();
            //String result="";
		//System.out.println(op);
            if(op==opreq)
	    { //request
		for(int i=0;i<mac.length;i++)
		{
			if(desip.equals(ip[i]))
			{
				String desmac=mac[i];
				//System.out.println(desmac);
				 data=data+"::"+op+"::"+dip+"::"+dmac+"::"+desip+"::"+desmac+"::"+devport;

			}
			else
			{
				String desmac=null;
				data=data+"::"+op+"::"+dip+"::"+dmac+"::"+desip+"::"+desmac+"::"+devport;

			}	
			
		}
		//data=data+"::"+op+"::"+dip+"::"+dmac+"::"+desip+"::"+destmac+"::"+devport;
		 senddata=data.getBytes();
		//System.out.println(senddata);
			DatagramPacket sendPacket =
                  	new DatagramPacket(senddata, senddata.length,InetAddress.getByName(dip),devport);
                  	dp.send(sendPacket); 
           }
	   else
	   {
			//data=recdata+"::"+op+"::"+dip+"::"+dmac+"::"+desip+"::"+destmac+"::"+devport;
			senddata=data.getBytes();
			for(int i=0;i<ip.length;i++)
			{
			if(dip.equals(ip[i]))
			{
				 desport=portlist[i];
			}	
			}
			DatagramPacket sendPacket =
                  	new DatagramPacket(senddata, senddata.length,InetAddress.getByName(desip),desport);
                  	dp.send(sendPacket);
           }
	}
	catch(Exception e)
	{
		System.out.println(e);
		//e.printStackTrace();
	}	
	}
	public static void main(String args[])throws IOException
	{
		int swport=Integer.parseInt(args[0]);
		Switch s=new Switch(swport);
		s.t1.start();
		//t2.start();
	}
}
