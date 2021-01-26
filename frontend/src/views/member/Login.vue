<template>
    <div class="wrapper">
        <transition name="slide">
            <div v-if="active" class="ticket">
                <p class="title">Welcome to NUVO</p>
                <div class="ticket__content">
                    <input type="email" placeholder="email" autofocus />
                    <v-icon> mdi-ticket-account </v-icon>
                    <input type="password" placeholder="password" autofocus />
                    <v-icon> mdi-key </v-icon>
                </div>
            </div>
        </transition>
        <p>Doesn't have account?</p>
        <button @click="login">
            <span class="state">Log-in</span>
        </button>
    </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
    data: function () {
        return {
            active: true,
        };
    },
    methods: {
        login: function () {
            this.active = !this.active;
        },
    },
});
</script>

<style scoped>
.wrapper {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    min-height: 100%;
    padding: 20px;
    background: rgba(4, 40, 68, 0.85);
}

.ticket {
    position: relative;
    width: 25%;
    height: 60%;
    box-sizing: border-box;
    margin: 10px auto 0;
    padding: 20px;
    border-radius: 10px;
    background: #fbfbfb;
    box-shadow: 2px 2px 15px 0px #ab9b0d;
}
.ticket:before,
.ticket:after {
    content: "";
    position: absolute;
    left: 0;
    height: 4px;
    width: 100%;
}
.ticket:before {
    top: -4px;
    background: radial-gradient(
            circle,
            transparent,
            transparent 50%,
            #fbfbfb 50%,
            #fbfbfb 100%
        ) -7px -8px/16px 16px repeat-x;
}
.ticket:after {
    bottom: -3px;
    background: radial-gradient(
            circle,
            transparent,
            transparent 50%,
            #fbfbfb 50%,
            #fbfbfb 100%
        ) -7px -2px/16px 16px repeat-x;
}
.ticket__content {
    box-sizing: border-box;
    height: 80%;
    width: 100%;
    border: 6px solid #d8d8d8;
}
.ticket input {
    display: block;
    padding: 15px 10px;
    margin-bottom: 10px;
    width: 100%;
    border: 1px solid #ddd;
    transition: border-width 0.2s ease;
    border-radius: 2px;
    color: #ccc;
}
.ticket input + i.v-icon.v-icon.v-icon {
    color: #fff;
    font-size: 1em;
    position: absolute;
    margin-top: -47px;
    opacity: 0;
    left: 0;
    transition: all 0.1s ease-in;
}
.ticket input:focus {
    outline: none;
    color: #444;
    border-color: #2196f3;
    border-left-width: 35px;
}
.ticket input:focus + i.v-icon.v-icon {
    opacity: 1;
    left: 30px;
    transition: all 0.25s ease-out;
}

.ticket .title {
    color: #444;
    font-size: 1.2em;
    font-weight: bold;

    border-bottom: 1px solid #eee;
}
.ticket button {
    width: 100%;
    padding: 10px 10px;
    background: #000000;
    color: #fff;
    display: block;
    border: none;
    border-radius: 10px;
    margin-top: 10px;
    border: 0px solid rgba(0, 0, 0, 0.1);
    border-radius: 0 0 2px 2px;
    transform: rotateZ(0deg);
    transition: all 0.1s ease-out;
    border-bottom-width: 7px;
}

.ticket:not(.loading) button:hover {
    box-shadow: 0px 1px 3px #444;
}
.ticket:not(.loading) button:focus {
    border-bottom-width: 4px;
}
.ticket.loading button {
    max-height: 100%;
    padding-top: 50px;
    top: -50%;
}
.ticket.ok button {
    background-color: #8bc34a;
}

/* transition slide 효과 */
.slide-leave-active,
.slide-enter-active {
    transition: 0.9s;
}
.slide-enter {
    transform: translateY(100%);
}
.slide-leave-to {
    transform: translateY(100%);
}
</style>