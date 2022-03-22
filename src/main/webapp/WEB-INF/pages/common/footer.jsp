<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</div>
</body>
<style>
    .footer {
        height: 40px;
        border-top: 1px solid #6c757d;
        position: fixed;
        bottom: 0;
        left: 0;
        width: 100%;
        display: flex;
        padding: 0 20px;
        align-content: center;
        background: #8080805e;
        justify-content: space-between;
    }
    .left {
        text-align: left;
        display: flex;
        align-content: center;
        position: relative;
    }
    .right {
        text-align: right;
        position: relative;
    }
    .txt_left {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        white-space: nowrap;
    }
    .before {
         position: relative;
     }
    .before::before {
        content: "";
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        left: -20px;
        width: 6px;
        height: 6px;
        border-radius: 50%;
        background: #343a40;
    }
</style>
<footer class="footer">
    <div class="left">
        <p class="txt_left">&copy; 2022 WaterSystem.com</p>
    </div>
    <div class="right">
        <p style="margin-right: 34px; right: 55px" class="txt_left">SQA1</p>
        <p class="before txt_left">Nhóm 15</p>
    </div>
</footer>
<script>
    $.datepicker.setDefaults(
        $.extend(
            {'dateFormat': 'yy/mm/ff'},
            $.datepicker.regional['ja']
        )
    );
</script>