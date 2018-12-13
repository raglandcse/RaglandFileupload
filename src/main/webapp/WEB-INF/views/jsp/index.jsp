<html>

<body>
<h1>File Upload</h1>

<form method="POST" action="${pageContext.request.contextPath}/index" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>