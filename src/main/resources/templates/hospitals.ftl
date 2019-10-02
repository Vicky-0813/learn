<html>
<head></head>
<body>
<table>
<#list hospitals as hospital>
<tr>
<td>${hospital.name} </td><td>${hospital.phone} </td>
</tr>
</#list>
</table>
</body>
</html>