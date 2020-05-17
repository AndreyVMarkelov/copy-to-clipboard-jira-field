AJS.$().ready(function() {
    var clipboardJSTrigger = function() {
        var clipboard = new ClipboardJS(".copy-to-clipboard");
        clipboard.on('error', function(e) {
            console.error('Action:', e.action);
            console.error('Trigger:', e.trigger);
        });
        $(".copy-to-clipboard").tooltip({trigger: "manual"});
        $(".copy-to-clipboard").bind("click", function(event) {
            $(".copy-to-clipboard").tooltip("show");
            setTimeout(function() {
                $(".copy-to-clipboard").tooltip("hide");
            }, 1000);
        });
    }
    JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (event, context, reason) {
        clipboardJSTrigger();
    });
    clipboardJSTrigger();
});
