function getURLParam(param) {
    var pageURL = window.location.search.substring(1);
    var variablesURL = pageURL.split('=');

    return variablesURL[1];
}

function getPlane() {
    $.ajax({
        url : "ws/planes",
        type : "GET",
        dataType : "json",

        success : function(data) {
            getTasks(data);
        },
    });
}

function getTasks(data) {
    var plane = _.filter(data, function(item) {
        return item.tailNumber == getURLParam("plane");
    });

    console.log(plane);
    console.log(JSON.stringify(plane));
    $.ajax({
        url : "ws/tasks/" + plane[0].id,
        type : "GET",
        dataType : "json"
    }).done(function(data) {
        printTasks(data);
    });
}

function printTasks(data) {
    var template = _.template($("#list_tasks").html());
    var task = template({
        "item" : data
    });

    console.log(task);
    $("#tasks").append(task);
}

function deleteTask(id_task){
    $.ajax({
        url: "ws/tasks/"+id_task,
        type: "DELETE",
        dataType : "json",
        success : function (data) {
            getTasks(data);
        }
    })
}


$(document).ready(function() {
    document.getElementById("plane_number").innerHTML = getURLParam("plane");
    getPlane();
    $("#tasks").on("click","a.btn-danger",function () {
        var id_task=$(this).attr("id");
        console.log(id_task);
        deleteTask(id_task);
        $(this).closest('tr').remove();

    });
    $("#tasks").on("click","#edit_btn",function () {
        alert("what?");
    });
    //
    // (function(){
    // 'use strict';
    // var $ = jQuery;
    // $.fn.extend({
    // filterTable: function(){
    // return this.each(function(){
    // $(this).on('keyup', function(e){
    // $('.filterTable_no_results').remove();
    // var $this = $(this),
    // search = $this.val().toLowerCase(),
    // target = $this.attr('data-filters'),
    // $target = $(target),
    // $rows = $target.find('tbody tr');
    //
    // if(search == '') {
    // $rows.show();
    // } else {
    // $rows.each(function(){
    // var $this = $(this);
    // $this.text().toLowerCase().indexOf(search) === -1 ? $this.hide() :
    // $this.show();
    // })
    // if($target.find('tbody tr:visible').size() === 0) {
    // var col_count = $target.find('tr').first().find('td').size();
    // var no_results = $('<tr class="filterTable_no_results"><td
    // colspan="'+col_count+'">No results found</td></tr>')
    // $target.find('tbody').append(no_results);
    // }
    // }
    // });
    // });
    // }
    // });
    // $('[data-action="filter"]').filterTable();
    // })(jQuery);
    //
    // $(function(){
    // // attach table filter plugin to inputs
    // $('[data-action="filter"]').filterTable();
    //
    // $('.container').on('click', '.panel-heading span.filter', function(e){
    // var $this = $(this),
    // $panel = $this.parents('.panel');
    //
    // $panel.find('.panel-body').slideToggle();
    // if($this.css('display') != 'none') {
    // $panel.find('.panel-body input').focus();
    // }
    // });
    // $('[data-toggle="tooltip"]').tooltip();
    // });
});