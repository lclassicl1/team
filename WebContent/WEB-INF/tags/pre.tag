<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="value" type="java.lang.String" required="true" %>
<%
  value.replace("\n","\n<br>");
  value.replace("&","&amp;");
  value.replace("<","&lt;");
  value.replace(">","&gt;");	
%>

<%=value %>