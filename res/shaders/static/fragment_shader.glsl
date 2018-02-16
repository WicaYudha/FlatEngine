#version 400 core

out vec4 out_Colour;

in vec3 pass_normal;
in vec2 pass_textureCoordinates;
in vec3 reflectedVector;
in vec3 refractedVector;

const vec3 lightDirection = normalize(vec3(0.2, -1.0, 0.3));
const float ambient = 0.3;

uniform sampler2D modelTexture;
uniform samplerCube enviroMap;

void main(void){
	float brightness = max(dot(-lightDirection, normalize(pass_normal)), 0.0) + ambient;
	out_Colour = texture(modelTexture, pass_textureCoordinates) * brightness;
	
	vec4 reflectedColour = texture(enviroMap, reflectedVector);
	vec4 refractedColour = texture(enviroMap, refractedVector);
	
	vec4 enviroColour = mix(reflectedColour, refractedColour, 1/1.33);
	
	out_Colour = mix(out_Colour, enviroColour, 0.8);
}