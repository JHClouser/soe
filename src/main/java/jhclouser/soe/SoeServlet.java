package jhclouser.soe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Efficiently determines all prime numbers less than a given positive integer. The sieve of Eratosthenes is the
 * underlying algorithm in this implementation.
 *
 * Not hardened to gracefully handle positive integers less than 3.
 *
 * @author Justin Clouser
 */
@SuppressWarnings("serial") // Serialization for this class is unnecessary.
@WebServlet("/soe")
public class SoeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bound = Integer.parseInt(request.getParameter("bound"));
		List<Boolean> allPrimeIndicesTrue = determinePrimesLessThan(bound);

		PrintWriter printWriter = response.getWriter();
		printWriter.println("<html><body>");
		for (int i = 0; i < allPrimeIndicesTrue.size(); i++) {
			if (Boolean.TRUE.equals(allPrimeIndicesTrue.get(i))) {
				printWriter.println("<p>" + i + "</p>");
			}
		}
		printWriter.println("</body></html>");
	}

	private List<Boolean> determinePrimesLessThan(int bound) {
		// Creates a list with size equal to the bound and where each element is the value "true."
		List<Boolean> allIndicesTrue = new ArrayList<Boolean>(bound);
		for (int i = 0; i < bound; i++) {
			allIndicesTrue.add(Boolean.TRUE);
		}

		List<Boolean> updatingListElements = allIndicesTrue;
		updatingListElements.set(0, Boolean.FALSE);
		updatingListElements.set(1, Boolean.FALSE);

		// Updates the element in the list to "false" for each index that is a multiple of a prime.
		for (int i = 2; i < Math.sqrt(bound); i++) {
			if (Boolean.TRUE.equals(updatingListElements.get(i))) {
				int iSquared = Double.valueOf(Math.pow(i, 2)).intValue();
				for (int j = 0; iSquared + (i * j) < bound; j++) {
					updatingListElements.set(iSquared + (i * j), Boolean.FALSE);
				}
			}
		}

		List<Boolean> allPrimeIndicesTrue = updatingListElements;

		return allPrimeIndicesTrue;
	}
}