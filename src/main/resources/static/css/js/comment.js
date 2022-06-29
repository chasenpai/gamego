var form = document.deleteForm;

        function delete(){

            if(form.createdBy.value != form.user.value){
                alert("작성자만 삭제할 수 있습니다.");
                return;
            }else {
                form.submit();
            }

        }