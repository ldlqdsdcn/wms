<#list  lineList as item>
function add${item.model}() {
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="${namespace}/${mode? uncap_first}_add${item.model}.action"/>';
editForm.submit();
}
function delete${item.model}s() {
if(isEmptyCheckBox('${item.model}_ids'))
{
alert('请先选择再删除');
return;
}
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="${namespace}/${mode? uncap_first}_delete${item.model}s.action"/>';
editForm.submit();
}
</#list>