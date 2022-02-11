APP_VERSION := $(shell awk 'NR==1{gsub(/"/, ""); print $$3}' project.clj)
APP_DIR := .
BUILD_TARGET := $(APP_DIR)/build

app-jar := $(APP_DIR)/target/{{name}}.jar
app-clj := $(shell find $(APP_DIR) -name "[^.]*.clj")
app-cljs := $(shell find $(APP_DIR) -name "[^.]*.cljs")

{{#graal?}}
IMAGE_VERSION := $(shell awk 'NR==1{gsub(/"/, ""); print $$2 ":" $$3}' project.clj)
IMAGES_TARGET := $(BUILD_TARGET)/images
app-image := $(IMAGES_TARGET)/{{name}}_$(APP_VERSION).tar.gz
{{/graal?}}

.PHONY: app
app: $(app-jar) {{#graal?}}$(app-image){{/graal?}}

.PHONY: clean
clean: clean-app
	rm -rf $(BUILD_TARGET)

print-%: ; @echo $* = [$($*)]

$(BUILD_TARGET):
	[ -d $@ ] || mkdir -p $@

.PHONY: clean-app
clean-app:
	cd $(APP_DIR) && lein clean{{#graal?}}
	rm -f $(app-image){{/graal?}}

$(app-jar): $(APP_DIR)/package.json $(app-clj) $(app-cljs)
	cd $(APP_DIR) && \
	lein run -m structor.builder/release || \
	lein run -m structor.builder/release

{{#graal?}}
$(app-image): $(APP_DIR)/Dockerfile $(app-jar)
	@[ -d $(@D) ] || mkdir -p $(@D)
	cd $(APP_DIR) && docker build -t $(IMAGE_VERSION) .
	docker save $(IMAGE_VERSION) | gzip > $(app-image)
{{/graal?}}

$(APP_DIR)/package.json:
	touch $@

$(APP_DIR)/**/*.clj:
	touch $@

$(APP_DIR)/**/*.cljs:
	touch $@

$(APP_DIR)/Dockerfile:
	touch $@
