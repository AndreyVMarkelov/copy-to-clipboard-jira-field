AJS.$().ready(function() {
    var clipboard = new Clipboard(".copy-to-clipboard");
    clipboard.on('error', function(e) {
        console.error('Action:', e.action);
        console.error('Trigger:', e.trigger);
    });
});
