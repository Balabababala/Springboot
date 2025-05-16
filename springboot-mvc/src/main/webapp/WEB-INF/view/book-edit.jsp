<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/ssr/book/update/${book.id}">
				書名: <input type="text" name="name" required /><br />
				價格: <input type="number" name="price" step="0.1" required /><br />
				數量: <input type="number" name="amount" required /><br />
				出刊: <input type="checkbox" name="pub"  /><br />
				<button type="submit">送出</button>
	</form>
</body>
</html>