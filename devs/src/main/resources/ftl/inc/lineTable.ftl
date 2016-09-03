<#list lineList as line>
<tr>
    <td colspan="4" style="padding-left: 0px;">
        <!-- table 页 bgn -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                        <td  style="height: 26px;width: 90px;" align="center" background="../images/table_page_1.gif">
                            ${line.label}
                        </td>
                <td background="../images/table_page_bg.gif" width="*"  style="height: 26px;"><div class="C_S_F_L">
                    &nbsp;
                </div></td>
            </tr>
        </table>
        <!-- table 页 end -->
    </td>

</tr>
<#if !line.trl>
<tr>
    <td colspan="2" style="padding-left: 20px;text-align: left;">
        <input value="添加${line.label}"
        type="button" class="input_submit"
        onclick="javascript:add${line.model}()"/> &nbsp;&nbsp;
        <input class="input_submit" type="button"  value="删除选中${line.label}"
        onclick="javascript:delete${line.model}s()"/>
    </td>
    <td colspan="2"></td>
</tr>
</#if>
<tr>
    <td colspan="4">
        <table id="${line.model}Table" class="table">
            <thead>
            <th>
                <input type="checkbox"
                       onclick="selectAll('${line.model}_ids',this);"/>
            </th>
            <th>序号</th>
            <#list line.propertyList as prop>
                <#if prop.prop!='id'&&prop.prop!=((mode? uncap_first)+'Id')>
                    <#if prop.prop!='orgId'&&prop.prop!='userId'&&prop.prop!='clientId'&&prop.prop!='created'&&prop.prop!='createdby'&&prop.prop!='updated'&&prop.prop!='updatedby'>
            <th >${prop.label}</th>
                    </#if>
                </#if>
            </#list>
            </thead>
            <tbody>
            <s:iterator value="${line.model?uncap_first}List" status="st">

                <tr class="<s:property value="#st.index%2==0?'odd':'even'"/>">
                    <td>
                        <input type="checkbox"  name="${line.model}_ids"
                               value="<s:property value="#st.index"/>"/>
                    </td>
                    <td>
                        <s:property value="#st.index+1"/>
                        <s:hidden
                                name="%{'${line.model?uncap_first}List['+#st.index+'].id'}"/>
                    </td>
    <#list line.propertyList as prop>
            <#if prop.prop!='id'&&prop.prop!=((mode? uncap_first)+'Id')>
               <#if prop.prop!='orgId'&&prop.prop!='userId'&&prop.prop!='clientId'&&prop.prop!='created'&&prop.prop!='createdby'&&prop.prop!='updated'&&prop.prop!='updatedby'>
                <td>
                    <s:textfield
                            name="%{'${line.model?uncap_first}List['+#st.index+'].${prop.prop}'}">
                    </s:textfield> <#if prop.required><span style="color:red">*</span></#if>
                    <#if !prop_has_next>
                        <s:hidden name="%{'${line.model?uncap_first}List['+#st.index+'].${(mode? uncap_first)+'Id'}'}"></s:hidden>
                    </#if>
                </td>
               </#if>
            </#if>
    </#list>


                </tr>

            </s:iterator>

            </tbody>
        </table>
    </td>
</tr>
</#list>