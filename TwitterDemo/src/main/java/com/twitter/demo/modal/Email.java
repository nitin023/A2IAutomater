package com.twitter.demo.modal;


    public class Email {
        private String subject;
        private String reciepent;
        private String template;

        public String getTemplate() {
            return template;
        }

        public String getSubject() {
            return subject;
        }

        public String getReciepent() {
            return reciepent;
        }

        private void setReciepent(String reciepent) {
            if (preValidate("reciepent", reciepent))
                this.reciepent = reciepent;
        }

        private void setSubject(String subject) {
            if (preValidate("subject", subject))
                this.subject = subject;
        }

        public void setTemplate(String template) {
            this.template = template;
        }


        public Email(String subject,String reciepent,String template) {
            this.setSubject(subject);
            this.setReciepent(reciepent);
            this.setTemplate(template);
        }

        private boolean preValidate(String classFieldName, Object emailInfo) {
            boolean response = false;
            switch (classFieldName) {
                case "subject":
                    if (ValidateString(emailInfo.toString())) {
                        response = true;
                    }
                    break;

                case "reciepent":
                    if (ValidateString(emailInfo.toString())) {
                        response = true;
                    }
                    break;
                case "template":
                    if (ValidateString(emailInfo.toString())) {
                        response = true;
                    }
                    break;
                default:
                    response = false;
            }
            return response;
        }

        private boolean ValidateString(String string) {
            boolean response = false;
            if (string != null && !string.isEmpty()) {
                response = true;
            }
            return response;

        }
    }
