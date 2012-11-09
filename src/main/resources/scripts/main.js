function setGroup(){
    test = document.getElementById('myid');
    alert(test);
}

/* Store issue id and their text */
if(typeof issue_data == "undefined") {
    var issue_data = new Object();
}

function store_issue(issue_id, value){
   issue_data[issue_id] = value;
}

function glueme(baseurl, issue_id){
    ZeroClipboard.setMoviePath(baseurl + '/download/resources/ru.mail.plugins.clipcopier.copy-to-clipboard/zeroclipboard.swf');
    var clip = new ZeroClipboard.Client();

    clip.addEventListener( 'onComplete', function(client, text){
        alert("Copied text to clipboard:\n" + text );
        AJS.$("#copy_div_"+issue_id).css('background-color','#00FF00');
    });

    clip.setText(issue_data[''+issue_id]);
    clip.glue( 'copy_div_' + issue_id );
}
