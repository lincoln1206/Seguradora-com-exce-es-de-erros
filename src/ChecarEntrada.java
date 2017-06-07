import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import javax.swing.JOptionPane;

public class ChecarEntrada {//CLASSE QUE CHECA AS ENTRADAS

	public boolean isNumeric_(String s) {
		try {
			Integer.parseInt(s);
			if (s.indexOf(",") != -1) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	public boolean isNumeric(String s) {//CHECA SE A ENTRADA É NUMÉRICA
		try {
			if (s != null && s.length() > 0 && s.length() <= 10 && isNumeric_(s) == true) {
				return true;
			} else if (s.length() == 0 && s != null) {
				throw new DigitouNada();
			} else if (s != null && isNumeric_(s) == false || s.length() > 10 || s.indexOf(",") != -1) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
			return false;
		} catch (DigitouNada e) {
			return false;
		}
		return true;
	}

	private static ThreadLocal<NumberFormat> brazilianCurrencyFormat = new ThreadLocal<NumberFormat>() {
		@Override
		protected NumberFormat initialValue() {
			return new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		}
	};

	public boolean isCurrency_(String s) {
		s = s.trim();
		ParsePosition pos = new ParsePosition(0);
		brazilianCurrencyFormat.get().parse(s, pos);
		return pos.getIndex() == s.length();

	}

	public boolean isCurrency(String s) {//CHECA SE É DINHEIRO
		try {
			if (s != null && s.length() > 0 && s.length() <= 13 && isCurrency_(s) == true && s.indexOf(",") == -1) {
				return true;
			} else if (s.length() == 0 && s != null) {
				throw new DigitouNada();
			} else if (s.length() > 13 || s.indexOf(",") != -1 || isCurrency_(s) == false) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
			return false;
		} catch (DigitouNada e) {
			return false;
		}
		return true;
	}

}
