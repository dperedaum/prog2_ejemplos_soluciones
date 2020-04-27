/**
 * 
 */
package uy.edu.um.utils;

/**
 * @author danielpereda
 *
 */
public class StringUtilities {

	/**
	 * Esta función resta a cada caracter de la palabra oName nValue posiciones,
	 * generando un nueva cadena de carácteres que se retorna.
	 * @param oName
	 * @param nValue
	 * @return
	 */
	public static String decodeOffset(String oName, int nValue) {
		String sValue = "";

		if (oName != null && !oName.equals("")) {

			String oTempValue = oName;

			while (!oTempValue.equals("")) {

				int nChar = (int) Integer.valueOf(oTempValue.substring(
						oTempValue.length() - 2, oTempValue.length())) - nValue;

				sValue = sValue + ((char) nChar);

				oTempValue = oTempValue.substring(0, oTempValue.length() - 2);

			}

		}

		return sValue;
	}
	
}
