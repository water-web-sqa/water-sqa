<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</div>
</body>
<footer style="background-color: yellow">
</footer>
<script>
    $.datepicker.setDefaults(
        $.extend(
            {'dateFormat': 'yy/mm/ff'},
            $.datepicker.regional['ja']
        )
    );
</script>