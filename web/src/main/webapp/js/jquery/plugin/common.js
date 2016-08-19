/**
 * Created by admin on 2016/8/19.
 */
var myAlert = {
    alert: function(message, func) {
        var me = myAlert;

        $('#simpleAlertModal').find('#simpleAlertModalBody').text(message);
        if(func) {
            $('#simpleAlertModal').on('hidden.bs.modal', function(e) {
                func();
            });
        }
        $('#simpleAlertModal').modal();
    },

    alertError: function(message, func) {
        var me = myAlert;

        $('#simpleAlertModal').find('#simpleAlertModalBody').text(message);
        $('#simpleAlertModal').on('show.bs.modal', function(e) {
            $('#simpleAlertModal').addClass('modal-danger');
            $('#simpleAlertModal').find('#btnClose').removeClass('btn-primary').addClass('btn-outline');
        });
        $('#simpleAlertModal').on('hidden.bs.modal', function(e) {
            $('#simpleAlertModal').removeClass('modal-danger');
            $('#simpleAlertModal').find('#btnClose').removeClass('btn-outline').addClass('btn-primary');
            if(func) {
                func();
            }
        });
        $('#simpleAlertModal').modal();
    },

    alertWarning: function(message) {
        var me = myAlert;

        $('#simpleAlertModal').find('#simpleAlertModalBody').text(message);
        $('#simpleAlertModal').on('show.bs.modal', function(e) {
            $('#simpleAlertModal').addClass('modal-warning');
            $('#simpleAlertModal').find('#btnClose').removeClass('btn-primary').addClass('btn-outline');
        });
        $('#simpleAlertModal').on('hidden.bs.modal', function(e) {
            $('#simpleAlertModal').removeClass('modal-warning');
            $('#simpleAlertModal').find('#btnClose').removeClass('btn-outline').addClass('btn-primary');
        });
        $('#simpleAlertModal').modal();
    },

    confirm: function(message, confirmFunc, cancelFunc) {
        $('#simpleConfirmModal').on('hidden.bs.modal', function (e) {
            $('#simpleConfirmModal').find('#btnConfirm').unbind('click');
        });

        $('#simpleConfirmModal').find('#simpleConfirmModalBody').text(message);
        $('#simpleConfirmModal').find('#btnConfirm').one('click', function() {
            confirmFunc();
        });
        if(cancelFunc) {
            $('#simpleConfirmModal').find('#btnCancel').one('click', function() {
                cancelFunc();
            });
        }

        $('#simpleConfirmModal').modal();
    },

    confirmWarning: function(message, confirmFunc, cancelFunc) {
        $('#simpleConfirmModal').on('hidden.bs.modal', function (e) {
            $('#simpleConfirmModal').find('#btnConfirm').unbind('click');
        });

        $('#simpleConfirmModal').find('#simpleConfirmModalBody').text(message);
        $('#simpleConfirmModal').on('show.bs.modal', function(e) {
            $('#simpleConfirmModal').addClass('modal-warning');
            $('#simpleConfirmModal').find('#btnCancel').removeClass('btn-default').addClass('btn-outline');
            $('#simpleConfirmModal').find('#btnConfirm').removeClass('btn-primary').addClass('btn-outline');
        });
        $('#simpleConfirmModal').on('hidden.bs.modal', function(e) {
            $('#simpleConfirmModal').removeClass('modal-warning');
            $('#simpleConfirmModal').find('#btnCancel').removeClass('btn-outline').addClass('btn-default');
            $('#simpleConfirmModal').find('#btnConfirm').removeClass('btn-outline').addClass('btn-primary');
        });

        $('#simpleConfirmModal').find('#btnConfirm').one('click', function() {
            confirmFunc();
        });
        if(cancelFunc) {
            $('#simpleConfirmModal').find('#btnCancel').one('click', function() {
                cancelFunc();
            });
        }

        $('#simpleConfirmModal').modal();
    }
};
