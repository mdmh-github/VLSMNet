public class VLSMNet {
	public static void main(String args[]) throws Exception{
		IP ip = new IP(192, 168, 1, 1, 20);
		SubNet subred = ip.getSubNet(500, SubNet.HOST);		
		System.out.println (subred);
	
	}
}
