package telran.calculations.net;

import java.io.IOException;

import telran.calculations.CalculatorProtocol;
import telran.net.TcpServer;
import telran.calculations.common.*;
public class TcpCAlculatorAppl {

	public static void main(String[] args) throws IOException {
		TcpServer server=
	new TcpServer(CalculationsConstants.PORT,
			new CalculatorProtocol());

	}

}
