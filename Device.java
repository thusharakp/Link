import java.io.*;
import java.net.*;
public class Device// implements Runnable
{
	//Thread t1,t2;
	int port,swport,opcode;
	String devip="", devmac="", swip="",swmac="",desmac="";
	byte[]senddata=new byte[1024];
	byte[]recvdata=new byte[1024];
	String ip[]={"192.168.0.125","165.165.79.1"};
        String mac[]={" d0:27:88:e8:37:6a ","8A:BC:E3:FA:EA"};
	String datae="",datan="";
	DatagramSocket dp;
	public Device(int p,String dip,String dmac,String sip,String smac,int sp ,int op )
	{
		//DatagramSocket dp=new DatagramSocket(p);
		port=p;
		devip=dip;
		devmac=dmac;
		swip=sip;
		swmac=smac;
		swport=sp;
		opcode=op;
	/*	try
		{	
			this.port=port;
			t1=new Thread(this);
			t2=new Thread(this);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}*/
		send(port);
	}
	/*public void run()
	{
		while(true)
		{
			//if(Thread.currentThread()==t1)
			//{
				try
				{
				 dp=new DatagramSocket(port);
				}
				catch(Exception e)
				{
					System.out.println("13"+e);
				}	
				send();
			//}
			else
			//{
			//	try
			//	{
			//		Thread.sleep(500);
			//	}
			//	catch(Exception e)
			//	{
			//		System.out.println(e);
			//	}
				//DatagramSocket dp=new DatagramSocket(port);
			//	recev();
			}
		}
	}*/
	public void send(int port)
	{
	      try
	     {
		//int po=port;
		//DatagramSocket 
		dp=new DatagramSocket(port);
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		System.out.println("enter the ip address of destination");
		String desip=br.readLine();
		//System.out.println("enter the port of destination");
		//int desport=Integer.parseInt(br.readLine());
		
		//String ip[]={"192.168.0.125","165.165.79.1"};
                //String mac[]={" d0:27:88:e8:37:6a ","8A:BC:E3:FA:EA:FA"};
		for(int i=0;i<mac.length;i++)
		{
			if(desip.equals(ip[i]))
			{
				String desmac=mac[i];
				//System.out.println(desmac);
				datae=datae+"::"+opcode+"::"+devip+"::"+devmac+"::"+desip+"::"+desmac+"::"+port;
				senddata=datae.getBytes();
			//DatagramPacket sendPacket =
                  	//new DatagramPacket(senddata, senddata.length,InetAddress.getByName(swip),swport);
                  	//dp.send(sendPacket); 
			}
			else
			{
				String desmac=null;
				datan=datan+"::"+opcode+"::"+devip+"::"+devmac+"::"+desip+"::"+"FF:FF:FF:FF:FF:FF"+"::"+port;
				//System.out.println(datan);
				senddata=datan.getBytes();
				//System.out.println(senddata);
			//DatagramPacket sendPacket = new DatagramPacket(senddata, senddata.length,InetAddress.getByName(swip),swport);
                  	//dp.send(sendPacket); 
			//recev();
				//String desmac=null;
			}
			//senddata=datan.getBytes();
		}
		if(desmac!=null)
		{
			//data=data+"::"+opcode+"::"+devip+"::"+devmac+"::"+desip+"::"+desmac+"::"+port;//msg,opcode,devip,devmac,desip,desmac,devport
			//senddata=datae.getBytes();
			DatagramPacket sendPacket =
                  	new DatagramPacket(senddata, senddata.length,InetAddress.getByName(swip),swport);
                  	dp.send(sendPacket); 
		}
		else
		{
			//datan=datan+"::"+opcode+"::"+devip+"::"+devmac+"::"+desip+"::"+"FF:FF:FF:FF:FF:FF"+"::"+port;
			//System.out.println(datan);
			//senddata=datan.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(senddata, senddata.length,InetAddress.getByName(swip),swport);
                  	dp.send(sendPacket); 
			recev();
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
			//e.printStackTrace();
		}
	}
	public void recev()
	{
		try
		{
		//DatagramSocket 
		//dp=new DatagramSocket(port);
		DatagramPacket receivePacket = new DatagramPacket(recvdata, recvdata.length);
                dp.receive(receivePacket);
		String recd = new String( receivePacket.getData());
		System.out.println("RECEIVED: " + recd);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void main(String args[])throws IOException
	{
		try
		{
		Device d=new Device(Integer.parseInt(args[0]),args[1],args[2],args[3],args[4],Integer.parseInt(args[5]),Integer.parseInt(args[6]));
		//d.t1.start();
		//d.t2.start();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
