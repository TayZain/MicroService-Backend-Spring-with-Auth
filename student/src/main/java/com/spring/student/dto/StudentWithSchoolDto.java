package com.spring.student.dto;
import lombok.Data;

public class StudentWithSchoolDto {
        private String id;
        private String name;
        private String genre;
        private SchoolDto school;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getGenre() {
                return genre;
        }

        public void setGenre(String genre) {
                this.genre = genre;
        }

        public SchoolDto getSchool() {
                return school;
        }

        public void setSchool(SchoolDto school) {
                this.school = school;
        }
}
