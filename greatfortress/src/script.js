/**
 * Created by alpaca on 10/1/15.
 */
"use strict";

/**
 * toggle comments to enable console debugging
 * @type {function(this:Console)}
 */
var log =
    console.log.bind(console);
//function(){};

function showError(err) {
    log.apply(this, arguments);
    if (arguments.length > 1) {
        err = '';
        for (var idx = 0; idx < arguments.length; ++idx) err += ' ' + (null === arguments[idx] ? 'null' : arguments[idx]);
    }
    alert('Error: ' + err);
}

function loadServer(baseName) {
    var defer = $.Deferred();
    if (!baseName.match(/\.json$/)) {
        baseName += '.json';
    }
    $.getJSON("data/" + baseName).done(function (data) {
        if (!data.title) data.title = baseName;
        return defer.resolve(data)
    }).fail(function () {
        defer.reject('File loading error: ', arguments[2])
    });
    return defer.promise();
}

/**
 * Load client-side files pair
 *
 * @param tsFile:File
 * @param timingFile:File
 * @returns {*}
 */
function loadClientPair(tsFile, timingFile) {
    var defer = $.Deferred();
    $.when(
        readFilePromise(tsFile),
        readFilePromise(timingFile)
    ).done(function (tsData, timingData) {
            defer.resolve({
                scriptfile: tsData,
                timingfile: timingData,
                columns: null,
                lines: null,
                title: tsFile.name
            });
        }).fail(defer.reject.bind(defer))
    ;
    return defer.promise();
}

/**
 * parse and simple-check json format
 *
 * @throws string
 * @param jsonData
 * @returns {*}
 */
function parseJsonData(jsonData) {
    var data = JSON.parse(jsonData);
    if (null === data) throw "Data file is empty";
    if (typeof(data.hasOwnProperty) !== 'function' || !data.hasOwnProperty('scriptfile')) throw "No scriptfile property (typescript data) in json";
    return data;
}

/**
 * Load client-side json package
 *
 * @param jsonFile
 * @returns {*}
 */
function loadClientJson(jsonFile) {
    var defer = $.Deferred();
    readFilePromise(jsonFile).done(function (jsonData) {
        try {
            var data = parseJsonData(jsonData);
            if (!data.title) data.title = jsonFile.name;
            return defer.resolve(data);
        } catch (e) {
            return defer.reject(e);
        }
    }).fail(defer.reject.bind(defer))
    ;
    return defer.promise();
}

/**
 * Read text file from input[type=file] and return promise with string resolution
 *
 * @param file:File
 * @returns {*}
 */
function readFilePromise(file) {
    var defer = $.Deferred();
    log('Loading local file: ', file);
    if (!file || !file instanceof File) {
        defer.reject("No file passed to read");
    } else {
        var reader = new FileReader();
        reader.onload = function (e) {
            defer.resolve(e.target.result);
        };
        reader.readAsText(file)
    }
    return defer.promise();
}

/**
 * Switch jqui tabs to console tab
 */
function switchToConsole() {
    $(document.body).tabs('option', 'active', 0);
}

/**
 * Start replay by loaded data
 * @param data:Object
 */
function startReplay(data) {
    if (data.title) {
        document.title = 'Replaying: ' + data.title;
        $('#replayTitle').text('('+data.title+')').show();
    } else {
        $('#replayTitle').hide();
    }
    $('#console').showterm(data);
    switchToConsole();
}

/**
 * Method to save replay combined from file-pair form into single json file
 * @param data
 */
function saveJson(data) {
    var jsonStr = JSON.stringify(data);
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(jsonStr));
    element.setAttribute('download', 'scriptReplay.json');
    element.style.display = 'none';
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
}

/**
 * main bindings
 */
$(function () {
    // jqui
    $(document.body).tabs({active: 1, disabled: []});
    $('body>div').off('keydown');   // force-disable jqui keybindings on tabs
    $("input[type=submit], button").button();

    $('#loadServerJson').find('form').submit(function () {
        loadServer($('#serverFile').val()).done(function (ret) {
            var columns = parseInt($('#termWidth').val());
            if (!isNaN(columns) && columns > 20) {
                ret.columns = columns;
            }
            startReplay(ret);
        }).fail(showError)
    });

    // processing of client-side files pair
    $('#loadPair').find('form').submit(function () {
        var fileTs = $('#clientFile')[0].files[0];
        var fileTiming = $('#clientTimingFile')[0].files[0];
        loadClientPair(fileTs, fileTiming).done(function (ret) {
            var columns = parseInt($('#termWidth').val());
            if (!isNaN(columns) && columns > 20) {
                ret.columns = columns;
            }
            startReplay(ret);
        }).fail(showError);
    });

    // processing of client-side single json
    $('#loadJson').find('form').submit(function () {
        var fileJson = $('#jsonFile')[0].files[0];
        loadClientJson(fileJson).done(startReplay).fail(showError);
    });

    // processing of client-side single json
    $('#loadPair').find('#makeAJson').click(function (ev) {
        ev.preventDefault();
        var fileTs = $('#clientFile')[0].files[0];
        var fileTiming = $('#clientTimingFile')[0].files[0];
        loadClientPair(fileTs, fileTiming).done(function (ret) {
            var columns = parseInt($('#termWidth').val());
            if (!isNaN(columns) && columns > 20) {
                ret.columns = columns;
            }
            saveJson(ret);
        }).fail(showError);
    });
})