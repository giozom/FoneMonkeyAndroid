
function dbg(msg) { console.debug(msg); }

var loadingScript;

function dm_init()
{
	loadingScript = true;
	var script = document.createElement('script');
	script.type = 'text/javascript';
	script.src = 'http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js';
//	try
//	{
		document.getElementsByTagName('head')[0].appendChild(script);
//	}
//	catch (err)
//	{
//		// Report no page was loaded
//	}
}

function waitForScriptLoad()
{
	if (typeof $ === 'undefined')
	{
		setTimeout("waitForScriptLoad()", 500);
	}
	else
	{
		monkeyStart();
	}
}

function monkeyStart()
{
	if (!loadingScript)
	{
		dm_init();
		if (typeof $ === 'undefined')
				setTimeout("waitForScriptLoad()", 250);
		return;
	}

	loadingScript = false;

	monkeyInit();
}

function dbg(msg) { console.debug(msg); }

function handleInput(el)
{
	if (el.attr('type').toLowerCase() === 'text')
	{
		el.bind('keypress.monkey', function(event)
		{
//			dbg('BIND ' + String.fromCharCode(event.keyCode));
			Monkey.addKeyPress('HTML_ID', $(this).attr('id'), $(this).attr('nodeName'), String.fromCharCode(event.keyCode));
		});
	}
	else if (el.attr('type').toLowerCase() === 'button')
	{
		el.bind('click.monkey', function(event)
		{
//			dbg('BIND CLICK ID ' + $(this).attr('id'));
			Monkey.addClick('HTML_ID', $(this).attr('id'), $(this).attr('nodeName'));
		});
	}
}

function handleDiv(el)
{
	el.bind('keypress.monkey', function(event)
	{
//		dbg('DIV BIND ' + String.fromCharCode(event.keyCode));
		Monkey.addKeyPress('HTML_ID', $(this).attr('id'), $(this).attr('nodeName'), String.fromCharCode(event.keyCode));
	});

	el.bind('click.monkey', function(event)
	{
//		dbg('DIV BIND CLICK ID ' + $(this).attr('id'));
		Monkey.addClick('HTML_ID', $(this).attr('id'), $(this).attr('nodeName'));
	});
}

function handleLink(el)
{
	el.bind('click.monkey', function(event)
	{
//		dbg('A BIND CLICK ID ' + $(this).attr('id'));
		Monkey.addClick('HTML_ID', $(this).attr('id'), $(this).attr('nodeName'));
	});
}

function walkTree(root)
{
	root.children().each(function()
	{
		var name = $(this).attr('nodeName');
dbg(name + '  ID ' + $(this).attr('id') + '  ' + $(this).attr('type'));
		if (name === 'INPUT') handleInput($(this));
		else if (name === 'A') handleLink($(this));
		else if (name === 'DIV')
		{
			handleDiv($(this));
			walkTree($(this));
		}
		else if (name === 'CENTER') walkTree($(this));
	});
}

function toKeyCode(c)
{
	c = c.toLowerCase();
	return c.charCodeAt(0) - 32;
}

function monkeyClick(id)
{
	dbg('monkeyClick(' + id + ')');

	$('#' + id).click();
	/*
	var e = jQuery.Event('monkeyevent');
	e.id = id;
	e.monkeyType = 'click';
	$('#' + id).trigger(e);
	*/
}

function monkeyKeyPress(id, c)
{
	dbg('monkeyKeyPress(' + id + ', ' + c + ')');

	var e = document.createEvent('TextEvent');
	e.initTextEvent('textInput',
                          true,
                          true,
                          null,
                          c);

	dbg('ID ' + $('#' + id).attr('id'));

	$('#' + id).focus();
	$('#' + id)[0].dispatchEvent(e);

		/*
	var e = jQuery.Event('monkeyevent');
	e.id = id;
	e.monkeyType = 'keydown';
	e.text = c;
	$('#' + id).trigger(e);

	e = jQuery.Event('monkeyevent');
	e.id = id;
	e.monkeyType = 'keyup';
	e.text = c;
	$('#' + id).trigger(e);

	e = jQuery.Event('monkeyevent');
	e.id = id;
	e.monkeyType = 'keypress';
	e.text = c;
	$('#' + id).trigger(e);
	*/
}

function monkeyInit()
{
	walkTree($('body'));

			/*
	$('input[type=text]').live('monkeyevent', function(dmevent)
	{
	//dmevent.stopPropogation();

	var id = $(this).attr('id');
	if (id != dmevent.id) return;

	if (dmevent.monkeyType === 'keydown' || dmevent.monkeyType === 'keyup')
	{
		var e = jQuery.Event(dmevent.monkeyType);
		e.keyCode = toKeyCode(dmevent.text);
		var c = dmevent.text.charAt(0);
		if (c >= 'A' && c <= 'Z') e.shiftKey = true;
		$(this).trigger(e);
	}
	else if (dmevent.monkeyType === 'keypress')
	{
		var e = jQuery.Event(dmevent.monkeyType);
		e.keyCode = toKeyCode(dmevent.text);
		var c = dmevent.text.charAt(0);
		if (c >= 'A' && c <= 'Z') e.shiftKey = true;
		$(this).trigger(e);

		var e = document.createEvent('TextEvent');
		e.initTextEvent('textInput',
                          true,
                          true,
                          null,
                          dmevent.text);

		$(this).focus();
		$(this)[0].dispatchEvent(e);
	}
	});

	$('input[type=button]').live('monkeyevent', function(dmevent)
	{
	//dmevent.stopPropogation();

	var id = $(this).attr('id');
	if (id != dmevent.id) return;

	if (dmevent.monkeyType === 'click')
	{
		$(this).click();
	}
	});
	*/
}

