public class IP {
	private int octeto[], subfijo;

	public IP(int o1, int o2, int o3, int o4, int s) {
		octeto = new int[4];
		octeto[0] = o1;
		octeto[1] = o2;
		octeto[2] = o3;
		octeto[3] = o4;
		subfijo = s;
	}

	public SubNet getSubNet(int cantidad, int modo) throws Exception {
		return new SubNet(this, cantidad, modo);
	}

	public String toString() {
		return octeto[0] + "." + octeto[1] + "." + octeto[2] + "." + octeto[3]
				+ " /" + subfijo;
	}

	public int getOcteto(int pos) {
		return (pos < 1 || pos > 4) ? -1 : octeto[pos - 1];
	}

	public int getSubfijo() {
		return subfijo;
	}
}
