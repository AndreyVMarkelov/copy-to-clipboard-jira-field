AJS.$().ready(function() {
    var clipboard = new ClipboardJS(".copy-to-clipboard");
    clipboard.on('error', function(e) {
        console.error('Action:', e.action);
        console.error('Trigger:', e.trigger);
    });
});
