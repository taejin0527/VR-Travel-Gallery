<template>
    <div id="menu">
        <!-- 추가할 메뉴는 추후에 수정-->
        <router-link tag="li" @click.native="closeMenuPage" to="/"
            >Home</router-link
        >
        <router-link tag="li" @click.native="closeMenuPage" to="/worldmap"
            >Main Page</router-link
        >

        <div
            class="cursor"
            :style="{ left: cursor.left, top: cursor.top }"
        ></div>
    </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
    name: "Menu",
    props: {
        isMenuPageOpen: Boolean,
    },
    data() {
        return {
            cursor: {
                left: "",
                top: "",
            },
        };
    },
    methods: {
        // 메뉴 끄기
        closeMenuPage: function () {
            console.log("hey!!!");
            this.$emit("closeMenuPage");
        },
    },
    mounted() {
        document.addEventListener("mousemove", (e) => {
            this.cursor.left = e.pageX + "px";
            this.cursor.top = e.pageY + "px";
        });
    },
});
</script>

<style scoped>
/* 화면 전체 랩핑 */
#menu {
    position: absolute;
    margin: 0;
    padding: 0;
    z-index: 1;
    background-color: #b2876f;
    height: 100vh;
    width: 100vw;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

li {
    list-style: none;
    text-decoration: none;
    margin: 10px 0;
    position: relative;
    display: inline-block;
    font-size: 4em;
    color: #ffffff;
}
.cursor {
    position: fixed;
    left: 0;
    height: 20px;
    width: 20px;
    border-radius: 50%;
    background: #ffffff;
    transform: translate(-50%, -50%);
    pointer-events: none;
    mix-blend-mode: difference;
}
li:hover ~ .cursor {
    transform: scale(4);
}
</style>