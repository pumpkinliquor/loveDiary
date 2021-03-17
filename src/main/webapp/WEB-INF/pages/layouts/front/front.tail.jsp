<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<c:if test="${lan!='EN'}">
</c:if>
<c:if test="${lan=='EN'}">
</c:if>
<div class="loader">
    <div class="main">
        <div class="s1">
            <div class="s b sb1"></div>
            <div class="s b sb2"></div>
            <div class="s b sb3"></div>
            <div class="s b sb4"></div>
        </div>
        <div class="s2">
            <div class="s b sb5"></div>
            <div class="s b sb6"></div>
            <div class="s b sb7"></div>
            <div class="s b sb8"></div>
        </div>

        <div class="bigcon">
            <div class="big b"></div>
        </div>
<%--        <span style="color: #fff;--%>
<%--    display: inline-block;--%>
<%--    width: 100px;--%>
<%--    text-align: center;--%>
<%--    margin-left: -52px;--%>
<%--    font-size: 10px;margin-top:40px;background:#000;">옜다<br>로딩이다</span>--%>


    </div>
</div>
<style type="text/css">
    .beforeAnswerArea {
/*         display: none; */
    }


    .loader .s2 {
        position: absolute;
        height: 50px;
        width: 50px;
        background-color: transparent;
        /*top: 50vh;*/
        /*left: 50%;*/
        transform: translate(-50%, -50%);
    }

    .loader .s1 {
        position: absolute;
        height: 50px;
        width: 50px;
        /*top: 50vh;*/
        /*left: 50%;*/
        transform-origin: center;
        transform: translate(-50%, -50%) rotate(45deg);
        background-color: transparent;
    }

    .loader .bigcon {
        position: absolute;
        height: 50px;
        width: 50px;
        /*top: 50vh;*/
        /*left: 50%;*/
        transform-origin: center;
        transform: translate(-50%, -50%) rotate(-45deg);
        background-color: transparent;
        animation: bigcon 2s infinite linear;
        animation-delay: 0.25s;
    }

    .loader .b {
        border-radius: 50%;
        position: absolute;
    }

    .loader .s {
        width: 10px;
        height: 10px;
        animation: small 2s infinite ease;
        box-shadow: 0px 2px rgba(0, 0, 0, 0.3);
        background-color: #46b9ff;
    }

    .loader .s:nth-child(1) {
        top: 0%;
        left: 0%;
    }

    .loader .s:nth-child(2) {
        top: 0%;
        right: 0%;
    }

    .loader .s:nth-child(3) {
        right: 0%;
        bottom: 0%;
    }

    .loader .s:nth-child(4) {
        bottom: 0%;
        left: 0%;
    }

    .loader .big {
        width: 9px;
        height: 9px;
        border-radius: 15px;
        box-shadow: 0px 0px 10px #54f7f8, 0px 0px 20px #54f7f8, 0px 0px 30px #54f7f8, 0px 0px 50px #54f7f8, 0px 0px 60px #54f7f8;
        z-index: 1;
        background-color: #54f7f8;
        animation: bigball 1s infinite linear;
    }

    .loader .sb1 {
        animation-delay: -1.75s;
    }

    .loader .sb6 {
        animation-delay: -1.5s;
    }

    .loader .sb2 {
        animation-delay: -1.25s;
    }

    .loader .sb7 {
        animation-delay: -1s;
    }

    .loader .sb3 {
        animation-delay: -0.75s;
    }

    .loader .sb8 {
        animation-delay: -0.5s;
    }

    .loader .sb4 {
        animation-delay: -0.25s;
    }

    .loader .sb5 {
        animation-delay: -0s;
    }

    @keyframes bigcon {
        0% {
            transform-origin: center;
            transform: translate(-50%, -50%) rotate(45deg);
        }
        100% {
            transform-origin: center;
            transform: translate(-50%, -50%) rotate(405deg);
        }
    }

    @keyframes small {
        0% {
            transform: scale(1);
            background-color: #46b9ff;
        }
        10% {
            transform: scale(1.3);
            background-color: #54f7f8;
        }
        15% {
            transform: scale(1);
        }
        25% {
            transform: scale(1);
            background-color: #46b9ff;
        }
        100% {
            transform: scale(1);
            background-color: #eaf7ff;
        }
    }

    .loader {
        position: fixed;
        font-size: 20px;
        margin: 100px auto;
        width: 1em;
        top: calc(40% - 50px);
        left: 49.8%;
        background-color: rgba(0, 0, 255, 0.3);
    }

    /*  .loader {
    color: #319aff;
    font-size: 20px;
    margin: 100px auto;
    width: 1em;
          top:calc(45% - 50px);
          left:49.5%;
    height: 1em;
    border-radius: 50%;
    position: fixed;
    text-indent: -9999em;
    -webkit-animation: load4 1.3s infinite linear;
    animation: load4 1.3s infinite linear;
    -webkit-transform: translateZ(0);
    -ms-transform: translateZ(0);
    transform: translateZ(0);
  }
  @-webkit-keyframes load4 {
    0%,
    100% {
      box-shadow: 0 -3em 0 0.2em, 2em -2em 0 0em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 0;
    }
    12.5% {
      box-shadow: 0 -3em 0 0, 2em -2em 0 0.2em, 3em 0 0 0, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 -1em;
    }
    25% {
      box-shadow: 0 -3em 0 -0.5em, 2em -2em 0 0, 3em 0 0 0.2em, 2em 2em 0 0, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 -1em;
    }
    37.5% {
      box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0em 0 0, 2em 2em 0 0.2em, 0 3em 0 0em, -2em 2em 0 -1em, -3em 0em 0 -1em, -2em -2em 0 -1em;
    }
    50% {
      box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 0em, 0 3em 0 0.2em, -2em 2em 0 0, -3em 0em 0 -1em, -2em -2em 0 -1em;
    }
    62.5% {
      box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 0, -2em 2em 0 0.2em, -3em 0 0 0, -2em -2em 0 -1em;
    }
    75% {
      box-shadow: 0em -3em 0 -1em, 2em -2em 0 -1em, 3em 0em 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 0, -3em 0em 0 0.2em, -2em -2em 0 0;
    }
    87.5% {
      box-shadow: 0em -3em 0 0, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 0, -3em 0em 0 0, -2em -2em 0 0.2em;
    }
  }
  @keyframes load4 {
    0%,
    100% {
      box-shadow: 0 -3em 0 0.2em, 2em -2em 0 0em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 0;
    }
    12.5% {
      box-shadow: 0 -3em 0 0, 2em -2em 0 0.2em, 3em 0 0 0, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 -1em;
    }
    25% {
      box-shadow: 0 -3em 0 -0.5em, 2em -2em 0 0, 3em 0 0 0.2em, 2em 2em 0 0, 0 3em 0 -1em, -2em 2em 0 -1em, -3em 0 0 -1em, -2em -2em 0 -1em;
    }
    37.5% {
      box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0em 0 0, 2em 2em 0 0.2em, 0 3em 0 0em, -2em 2em 0 -1em, -3em 0em 0 -1em, -2em -2em 0 -1em;
    }
    50% {
      box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 0em, 0 3em 0 0.2em, -2em 2em 0 0, -3em 0em 0 -1em, -2em -2em 0 -1em;
    }
    62.5% {
      box-shadow: 0 -3em 0 -1em, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 0, -2em 2em 0 0.2em, -3em 0 0 0, -2em -2em 0 -1em;
    }
    75% {
      box-shadow: 0em -3em 0 -1em, 2em -2em 0 -1em, 3em 0em 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 0, -3em 0em 0 0.2em, -2em -2em 0 0;
    }
    87.5% {
      box-shadow: 0em -3em 0 0, 2em -2em 0 -1em, 3em 0 0 -1em, 2em 2em 0 -1em, 0 3em 0 -1em, -2em 2em 0 0, -3em 0em 0 0, -2em -2em 0 0.2em;
    }
  }*/

</style>