#version 400 core

in vec3 position;
in vec 2 textureCoordinates;
in vec3 normal;

out vec3 pass_normal;
out vec2 pass_textureCoordinates;

void main(void){
	pass_textureCoordinates = textureCoordinates;
	pass_normal = normal;
	vec3 unitNormal = normalize(normal);
}