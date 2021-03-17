$(function() {
    "use strict";
    $(function() {
        $(".preloader").fadeOut();
    }), jQuery(document).on("click", ".mega-dropdown", function(e) {
        e.stopPropagation();
    });


    var set = function() {
        // if ($(window).width() < 768) {
        //     $('#vp').attr('content','user-scalable=no,width=768');
        // }else {
        //     $('#vp').attr('content','width=device-width, initial-scale=1');
        // }
        var isMini = $.cookie('mini')==='true' || $.cookie('mini')===true;
        var isMini2 =    isMini;
        //화면이 작으면 768 보다 작으면 무조건 반응형으로
        if(window.innerWidth<768) {
            isMini2 = true;
        }
            (window.innerWidth > 0 ? window.innerWidth : this.screen.width) < 4170  && isMini2==true ? ($("body").addClass("mini-sidebar"),
            $(".navbar-brand span").hide()) : ($("body").removeClass("mini-sidebar"),
            $(".navbar-brand span").show());
            var height = (window.innerHeight > 0 ? window.innerHeight : this.screen.height) - 1;
            (height -= 0) < 1 && (height = 1), height > 0 && $(".page-wrapper").css("min-height", height + "px");

    };
    $(window).ready(set);
    $(window).on("resize", set), $(".sidebartoggler").on("click", function() {



        $("body").hasClass("mini-sidebar") ? ($("body").trigger("resize"), $("body").removeClass("mini-sidebar"),
        $(".navbar-brand span").show()) : ($("body").trigger("resize"), $("body").addClass("mini-sidebar"), 
        $(".navbar-brand span").hide());
        //$('.')
        $.removeCookie('mini');
        $.cookie('mini', $("body").hasClass("mini-sidebar"), { expires: 7, path: '/' })

    }), $(".nav-toggler").click(function() {
        $("body").toggleClass("show-sidebar"), $(".nav-toggler i").toggleClass("ti-menu"), 
        $(".nav-toggler i").addClass("ti-close");
    }), $(".right-side-toggle").click(function() {
        $(".right-sidebar").slideDown(50), $(".right-sidebar").toggleClass("shw-rside");
    }), $(".floating-labels .form-control").on("focus blur", function(e) {
        $(this).parents(".form-group").toggleClass("focused", "focus" === e.type || this.value.length > 0);
    }).trigger("blur"), $(function() {
        for (var url = window.location, element = $("ul#sidebarnav a").filter(function() {
            return this.href == url;
        }).addClass("active").parent().addClass("active"); ;) {
            if (!element.is("li")) break;
            element = element.parent().addClass("in").parent().addClass("active");
        }
    }), $(function() {
        $('[data-toggle="tooltip"]').tooltip();
    }), $(function() {
        $('[data-toggle="popover"]').popover();
    }), $(function() {
        $("#sidebarnav").AdminMenu();
    }), $(".scroll-sidebar, .right-side-panel, .message-center, .right-sidebar").perfectScrollbar(), 
    $("body").trigger("resize"), $(".list-task li label").click(function() {
        $(this).toggleClass("task-done");
    }), $('a[data-action="collapse"]').on("click", function(e) {
        e.preventDefault(), $(this).closest(".card").find('[data-action="collapse"] i').toggleClass("ti-minus ti-plus"), 
        $(this).closest(".card").children(".card-body").collapse("toggle");
    }), $('a[data-action="expand"]').on("click", function(e) {
        e.preventDefault(), $(this).closest(".card").find('[data-action="expand"] i').toggleClass("mdi-arrow-expand mdi-arrow-compress"), 
        $(this).closest(".card").toggleClass("card-fullscreen");
    }), $('a[data-action="close"]').on("click", function() {
        $(this).closest(".card").removeClass().slideUp("fast");
    });

});