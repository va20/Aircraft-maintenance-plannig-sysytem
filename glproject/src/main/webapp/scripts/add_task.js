/**
 * Created by info on 30/03/17.
 */
$('#Task_name, #Mro').on('input', function() {
    if($('#Task_name').val() == "" || $('#Mro').val() == "")
        $('#Add').prop("disabled", true);
    // + COLORIER CHAMPS NON REMPLIS EN ROUGE PAR EXEMPLE
    else
        $('#Add').prop("disabled", false);
});


$('#Add').click(function() {
    var task_name = $('#Task_name').val();
    var mro = $('#Mro').val();
    var dead_line=$('#Deadline').val();
    var in_line=$('#default').val();
    var in_base=$('#primary').val();
    console.log(task_name+" "+mro+" "+dead_line+" "+in_base+" "+in_line);

    $.ajax({
        url : "ws/task/"+task_name+'/'+mro,
        type : "PUT",
        dataType : "json",

        success : function(data) {
            console.log(data);

            if(data) {
                $.cookie('name', task_name);
                location.href = "/edit_task.html";
            } else
                $("#span_con").html("ERREUR CHAMPS!!");
        },

        error : function(resultat, statut, erreur) {
            alert("ERROR !!!");
        }
    });
});