$(function () {
        const count = getAllCount();
        console.log(count)
    }
)

function getAllCount() {
    $.ajax({
        url: "/mlog/allCount",
        dataType: "json",
        async: false,
        success: function (result) {
            return result.data
        }
    })
}