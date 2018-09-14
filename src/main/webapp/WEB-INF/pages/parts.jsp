<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Parts list</title>

    <style type="text/css">
        h4 {
            font-family: Arial, sans-serif;
            font-size: 12px;
            border-collapse: collapse;
        }

        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            text-align: center;
            padding: 5px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            text-align: center;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<h3>Складские остатки</h3>

<form action="/parts/search" method="post">
    <table>
        <tr>
            <td>Наименование детали:
            </td>
            <td>
                <input type="text" name="keyWord" />
            </td>
            <td colspan="2">
                <input type="submit" value="Найти"/>
            </td>
        </tr>
    </table>
</form>

<h4>Выбрать:&nbsp;&nbsp;&nbsp;<a href="/parts/0/0">Все</a>&nbsp;&nbsp;<a href="/parts/2/0">Основные</a>&nbsp;&nbsp;<a href="/parts/3/0">Опции</a></h4>

<c:if test="${!empty listParts}">
    <table class="tg">
        <tr>
            <th width="80">№</th>
            <th width="180">Наименование</th>
            <th width="40">Основная</th>
            <th width="80">Кол-во</th>
            <th width="80">Изменить</th>
            <th width="80">Удалить</th>
        </tr>
        <c:forEach items="${listParts}" var="part">
            <tr>
                <td>${part.id}</td>
                <td>${part.partName}</td>

                <c:if test="${part.partBase}">
                    <td>Да</td>
                </c:if>
                <c:if test="${!part.partBase}">
                    <td>Нет</td>
                </c:if>

                <td>${part.partValue}</td>
                <td><a href="<c:url value='/edit/${part.id}'/>"><img width="14" height="14" src="\images\edit.png" alt="Edit"></a></td>
                <td><a href="<c:url value='/remove/${part.id}'/>"><img width="14" height="14" src="\images\reci.png" alt="Delete"></a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="6" align="center">
            <c:forEach var="i" begin="0" end="${totalPages}" step="1">
                <c:if test="${i == curPage}">${curPage+1}</c:if>
                <c:if test="${i != curPage}"><a href="/parts/${opt}/${i}">${i+1}</a></c:if>
            </c:forEach>
            </td>
        </tr>
    </table>
</c:if>
<br>
<br>
<table class="tg">
    <tr>
        <th width="160">Всего можно собрать:</th>
        <td width="40" align="center">${minBasePart}</td>
        <th width="80">компьютеров</th>
    </tr>
</table>
<br>

<h3>Добавить деталь:</h3>

<c:url var="addAction" value="/parts/add"/>

<form:form action="${addAction}"
           modelAttribute ="part">
    <table>
        <c:if test="${!empty part.partName}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="№"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="partName">
                    <spring:message text="Наименование детали: "/>
                </form:label>
            </td>
            <td>
                <form:input path="partName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="partBase">
                    <spring:message text="Деталь основная?"/>
                </form:label>
            </td>
            <td>
                <form:checkbox path="partBase"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="partValue">
                    <spring:message text="Количество деталей: "/>
                </form:label>
            </td>
            <td>
                <form:input path="partValue"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty part.partName}">
                    <input type="submit"
                           value="<spring:message text="Сохранить"/>"/>
                </c:if>
                <c:if test="${empty part.partName}">
                    <input type="submit"
                           value="<spring:message text="Добавить"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
