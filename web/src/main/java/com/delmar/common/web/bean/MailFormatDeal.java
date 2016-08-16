package com.delmar.common.web.bean;
/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月27日 下午3:41:33 
 * 类说明 
 */
public class MailFormatDeal {

private static MailFormatDeal instance=null;
	
	public MailFormatDeal() {
      
	}
	
	public static MailFormatDeal getInstance()
	{
		if (instance==null)
			instance=new MailFormatDeal();
		
		return instance;
	}
	
	
	

	public void fullDataTD(StringBuffer mailContent, String tdData) {
		this.fullDataTD(mailContent, tdData, "");
	}

	public void fullDataTD(StringBuffer mailContent, String tdData,
			String tdClass) {
		fullDataTD(mailContent, tdData, tdClass, "");
	}

	public void fullDataTD(StringBuffer mailContent, String tdData,
			String tdClass, String tagType) {
		fullDataTD(mailContent, tdData, tdClass, tagType, "");
	}

	public void fullDataTD(StringBuffer mailContent, String tdData,
			String tdClass, String tagType, String trClass) {

		if (tagType.equals("first"))
			mailContent.append("<tr id='tabledata' class='" + trClass
					+ "'>\r\n");

		if (tdClass.equals("")) {
			mailContent.append("<td>");
		} else {
			mailContent.append("<td class=\"" + tdClass + "\">");
		}
		mailContent.append(tdData);
		mailContent.append("</td>\r\n");

		if (tagType.equals("last"))
			mailContent.append("</tr>\r\n");
	}

	public String getTextOrDigit(String originStr, int type) {
		if (type == 0) // 字符
		{
			return originStr.replaceAll("\\d", "");
		} else {
			return originStr.replaceAll("\\D", "");
		}

	}

	public void writeMainDataTableHeader(StringBuffer mailContent,
			String headerTitle) {
		mailContent.append("<tr>");
		mailContent.append("<td>");
		mailContent
				.append("<table class='maintable' border='1' cellpadding='0' cellspacing='0'>\r\n");
		mailContent.append("<tr class='trheader'>\r\n");

		String[] headerA = headerTitle.split(",");

		for (int i = 0; i < headerA.length; i++) {
			if (!getTextOrDigit(headerA[i], 1).equals("")) {
				mailContent.append("<td style=\"width:"
						+ getTextOrDigit(headerA[i], 1) + ";wrap\">"
						+ getTextOrDigit(headerA[i], 0) + "</td>\r\n");
			} else {
				mailContent.append("<td style=\"wrap\">" + headerA[i]
						+ "</td>\r\n");
			}
		}

		mailContent.append("</tr>\r\n");
	}

	public String writeMainDataTableEnd() {

		StringBuffer mailContent = new StringBuffer();

		mailContent.append("</table>");
		mailContent.append("<br><br><br>");
		mailContent.append("</td>");
		mailContent.append("</tr>");

		return mailContent.toString();
	}

	public void writeMailHeader(StringBuffer mailContent, String title,
			String subTitle, String topImg) {
		mailContent.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");		
    	mailContent.append("<html>");
		mailContent.append("<head>");
		mailContent
				.append("<link href=\"/portal/common/web/css/mail/main.css\" type=\"text/css\" rel=\"stylesheet\" /> ");
		mailContent.append("</head> ");
		mailContent.append("<title>" + title + "</title>\r\n");
		mailContent.append("<body>\r\n");
		mailContent
				.append("<table border='0' cellpadding='0' cellspacing='0' class='toptable'>\r\n");
		mailContent.append("<tr>");
		mailContent.append("<td class='topimage'>");
		mailContent.append("<img src='" + topImg + "' border='0' alt='" + title
				+ "'>\r\n");
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<td class='maintitle'>");
		mailContent.append(title);
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<td class='subtitle'>");
		mailContent.append(subTitle);
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<td class='summarydata'>");
		mailContent.append("SUMMARYCONTENT"); // 需要进行统计的信息
		mailContent.append("</td>");
		mailContent.append("</tr>");

	}

	public String writeMailBottom(String noticeText, String noticeImg) {

		StringBuffer mailContent = new StringBuffer();
		mailContent.append("<tr>");
		mailContent.append("<td>");
		mailContent
				.append("<table border='0' cellpadding='0' cellspacing='0'>\r\n");
		mailContent.append("<tr>");
		mailContent.append("<td class='noticetext' >");
		mailContent.append(noticeText);
		mailContent.append("</td>");
		mailContent.append("<td class='bottomrightimg' >");
		mailContent.append("<img src='" + noticeImg + "' border='0'>");
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("<tr>");
		mailContent.append("<td colspan=2 class='bottomimg' >");
		mailContent
				.append("<img src='/portal/common/web/images/mail/bottom.png' border='0'>");
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("</table>");
		mailContent.append("</td>");
		mailContent.append("</tr>");
		mailContent.append("</table>");
		mailContent.append("</body>");
		mailContent.append("</html>");

		return mailContent.toString();
	}

}

