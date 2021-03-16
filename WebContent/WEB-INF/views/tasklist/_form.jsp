<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label>タスク内容</label><br/>
<input type ="text" name="content" value="${tasklist.content}"/>
<br/>
<br/>
<input type ="hidden" name="_token" value="${_token}"/>
<button type ="submit">登録</button>