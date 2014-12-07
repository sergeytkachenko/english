google.load('search', '1');
Ext.onReady(function () {
    $("input[name=word]").on("keyup", function (e) {
        switch(e.which) {
            case 38:
                up();
                return;
            case 40:
                down();
                return;
            case 13:
                pressWord();
                return;
        }
        $("div.content").addClass("searching");
        $("div.description-word").empty();
        if($(this).val()===""){
            $("ul.search-result").empty();
            return;
        }
        if($(this).val().length < 2) {return;}
        getData (
            '/words/search/findByTitleStartingWith',
            {
                word : $(this).val(),
                size : 10
            },
            function () {
                $("ul.search-result").empty();
                Ext.Array.each(this, function (o) {
                    $("ul.search-result").append("<li><a href='javascript:void(0)'>"+ o.title+"</a></li>");
                })
        });
    });
});

function getData (url, params, callback) {
    Ext.Ajax.request({
        url: url,
        params : params,
        method : "GET",
        success: function(response, opts) {
            var obj = Ext.decode(response.responseText);
            if(undefined == obj._embedded || undefined == obj._embedded.content) {
                callback.apply(null);
                return;
            }
            var data = obj._embedded.content;
            callback.apply(data);
        },
        failure: function(response, opts) {
            console.log('server-side failure with status code ' + response.status);
        }
    });
}

function pressWord () {
    var word =  $("ul.search-result li.active a, ul.search-result li:hover a").eq(0).text();
    if(word==="") {
        word = $("input[name=word]").val();
    }
    if(word==="") {return;}
    $("input[name=word]").val(word);
    $("ul.search-result").empty();
    getData("/word-description/search/findByTitle", {word:word}, function () {
        if(this[0] === undefined) {return;}
        var data = this[0];
        if(!data.description){
            $("div.description-word").html("No found result...")
            return;
        }
        var description = data.description.replace(/\n/ig, "<br>");
        $("div.description-word").html(description);
        searchImage(word);
    });

}

function up () {
    var li = $("ul.search-result li.active, ul.search-result li:hover").eq(0);
    if(li.length===0) {
        $("ul.search-result li:last").addClass("active");
        return;
    }
    $("ul.search-result li").removeClass("active");
    $(li).prev().addClass("active");
}

function down () {
    var li = $("ul.search-result li.active, ul.search-result li:hover").eq(0);
    if(li.length===0) {
        $("ul.search-result li:first").addClass("active");
        return;
    }
    $("ul.search-result li").removeClass("active");
    $(li).next().addClass("active");
}

function searchImage (word) {
    var imageSearch;
    imageSearch = new google.search.ImageSearch();
    imageSearch.setSearchCompleteCallback(this, searchComplete, null);
    imageSearch.execute(word);
    google.search.Search.getBranding('branding');
    function searchComplete() {
        if (imageSearch.results && imageSearch.results.length > 0) {
            var results = imageSearch.results;
            $("div.description-word").prepend("<img src='"+results[0].tbUrl+"' />");
        }
    }
}