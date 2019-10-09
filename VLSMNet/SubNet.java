import java.util.Hashtable;

public class SubNet {
	private Hashtable<Integer, int[]> tabla;

	public static final int REDES = 0, HOST = 1;

	private int numeroDeSubredes, tamanioDeSubred, bits, maxHost,modo,cantidad;

	private IP ip;

	SubNet(IP ip, int cantidad, int modo) throws Exception {
		tamanioDeSubred = 1;
		this.modo=modo;
		this.cantidad=cantidad;
		this.ip = ip;
		tabla = new Hashtable<Integer, int[]>();
		if (modo == HOST)
			calcularHosts(ip, cantidad, modo);
	}

	private void calcularHosts(IP ip, int cantidad, int modo) throws Exception {

		int cantidadBR = cantidad + 2, ini, fin;
		bits = 32 - ip.getSubfijo();
		maxHost = ((int) (Math.pow(2, bits) - 2));
		if (bits < 1 || bits == 32)
			throw new Exception("sufijo invalido");
		if (cantidad > maxHost)
			throw new Exception("Numero de Host imposible");
		while (tamanioDeSubred < cantidadBR)
			tamanioDeSubred <<= 1;
		numeroDeSubredes = (maxHost / tamanioDeSubred);
		fin = tamanioDeSubred - 1;
		for (int i = 0, contador = 0; i < maxHost; i += tamanioDeSubred, contador++) {
			ini = i;
			tabla.put(new Integer(contador), new int[] { ini, fin });
			fin += tamanioDeSubred;
		}
	}

	public int getNumeroDeSubRedes() {
		return numeroDeSubredes;
	}

	public int[] getRangoDeSubred(int red) {
		if (red < -1 || red > numeroDeSubredes)
			return null;
		return tabla.get(red);
	}

	public int getIpBroadcastDeSubred(int red) {
		if (red < -1 || red > numeroDeSubredes)
			return -1;
		return tabla.get(red)[1];
	}

	public int getIpDeSubred(int red) {
		if (red < -1 || red > numeroDeSubredes)
			return -1;
		return tabla.get(red)[0];
	}

	private void calcularRedes(IP ip, int cantidad, int modo) throws Exception {

	}

	public String toString() {
		String s = ip.toString() +//	"\nCalculado para "+cantidad+	(modo==HOST)?"Host":"Redes"+		
		":\nTamaño de las subredes :"
				+ tamanioDeSubred + "\nnumero de subredes :" + numeroDeSubredes
				+ "\nBits disponibles para Host: " + bits + "\n";
		for (int i = 0; i < numeroDeSubredes; i++)
			s += tabla.get(new Integer(i))[0] + " - "
					+ tabla.get(new Integer(i))[1] + "\n";
		return s;
	}
}