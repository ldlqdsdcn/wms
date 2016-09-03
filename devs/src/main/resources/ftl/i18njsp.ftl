<tr>
    <td colspan="4">
        <!-- table 页 bgn -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="90" height="26" align="center" background="../images/table_page_1.gif">
                    <delmar:message key="common.label.internationalization"/>
                </td>
                <td background="../images/table_page_bg.gif" width="*" height="26">
                    <div class="C_S_F_L">
                        &nbsp;
                    </div>
                </td>
            </tr>
        </table>
        <!-- table 页 end -->
    </td>
</tr>
<tr>
    <td colspan="4">
        <!-- table i18n bgn -->
        <table width="100%" cellpadding="10" cellspacing="0">
            <tr>
                <td align="center">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" class="table">
                                    <thead>
                                    <th>
                                        <delmar:message key="language.column.code"/>
                                    </th>
                                    <th>
                                        <delmar:message key="language.column.name"/>
                                    </th>
                                    <th>
                                        <fmt:message key="common.label.remark"/>
                                    </th>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="languageTrlList" status="st">
                                        <tr class="<s:property value=" #st.index%2==0?'odd':'even'"/>">
                                            <td>
                                                <s:hidden name="%{'languageTrlList['+#st.index+'].id'}"/>
                                                <s:textfield name="%{'languageTrlList['+#st.index+'].language'}"
                                                             readonly="true"/>
                                            </td>
                                            <td>
                                                <s:textfield
                                                        name="%{'languageTrlList['+#st.index+'].name'}"/>
                                            </td>
                                            <td>
                                                <s:textfield
                                                        name="%{'languageTrlList['+#st.index+'].remark'}"/>
                                            </td>
                                    </s:iterator>

                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <!-- table i18n end -->
    </td>
</tr>