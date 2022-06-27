function saveComment(gameId){

            var content = document.getElementById("content");
            if(isEmpty(content.value) == true) {
                    content.setAttribute("placeholder", "내용을 입력해주세요.");
                    content.focus();
                    return false;
            }

            var gameId = $("#gameId").val();
            var rating = $("#rating").val();
            var content = $("#content").val();
            var headers = {"Content-Type": "application/json", "X-HTTP-Method-Override": "POST"};

            $.ajax({
                url: '/comments/add/' + gameId,
                type: "POST,
                headers: headers,
                dataType: "json",
                data: JSON.stringify(gameId, rating, content),
                success: function(response){
                    if(response.result == false){
                        alert("등록 실패");
                        return false;
                    }

                }

            });

        }